package petfinder.petfinderapi.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.PetHasCaracteristica;
import petfinder.petfinderapi.entidades.Premio;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.CaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.PetHasCaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.PremioRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.PetRequest;
import petfinder.petfinderapi.resposta.PetPerfil;
import petfinder.petfinderapi.resposta.PremioDto;
import petfinder.petfinderapi.rest.DistanciaResposta;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.IdNotFoundException;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;
import petfinder.petfinderapi.service.exceptions.NoContentException;
import petfinder.petfinderapi.utilitarios.UploadFile;
import petfinder.petfinderapi.utilitarios.HashTable.HashTable;
import petfinder.petfinderapi.utilitarios.HashTable.PetsInstituicao;

@Service
public class ServicePet {

    // active profile (dev or prod)
    @Value("${spring.profiles.active}")
    private String activeProfile;
    
    @Autowired
    private PetRepositorio petRepository;

    @Autowired
    private UsuarioRepositorio userRepository;

    @Autowired
    private InstituicaoRepositorio instituicaoRepository;

    @Autowired
    private CaracteristicaRepositorio caracteristicaRepositorio;
    
    @Autowired
    private PetHasCaracteristicaRepositorio petHasCaracteristicaRepository;

    @Autowired
    private PremioRepositorio premioRepositorio;

    @Autowired
    private ServiceCep serviceCep;

    // success:     return pet profile data
    // fail:        throw IdNotFoundException
    public PetPerfil getPetPerfil(int petId, Integer userId) {

        // querying pet on database
        Optional<PetPerfil> optional = petRepository.findPetPerfilById(petId);

        // 200 ok
        if (optional.isPresent()) {
            PetPerfil petPerfil = optional.get();
            petPerfil.setDistancia(getDistanceBetweenPetAndUser(petPerfil.getCepInstituicao(), userId));
            return petPerfil;
        }

        // 404 not found
        throw new EntityNotFoundException(petId);
    }

    // return Integer value who represents distance between pet and user
    private Integer getDistanceBetweenPetAndUser(String cepPet, Integer userId) {

        // querying user by id
        Optional<Usuario> optUser = Optional.empty();
        if (Objects.nonNull(userId)) {
            optUser = userRepository.findById(userId);
        } 
        
        // filtering allowed users
        if (optUser.isPresent() && !optUser.get().getNivelAcesso().equalsIgnoreCase("sysadm")) {
            String cepUser = optUser.get().getEndereco().getCep();

            // case two CEP values are the same
            if (cepPet.equalsIgnoreCase(cepUser)) {
                return 0;
            }

            // getting distance between two ceps
            Optional<DistanciaResposta> optDistance = serviceCep.getDistanceBetweenTwoCeps(cepPet, cepUser);

            // return distance if it is non null value
            if (optDistance.isPresent()) {
                return optDistance.get().getDistancia();
            }
        }
        
        // case user is not found, return null
        return null;
    }

    public List<PetPerfil> getPetPerfilByInstituicaoId(int id) {

        // instituicao exists
        if (instituicaoRepository.existsById(id)) {

            List<PetPerfil> list = petRepository.findPetPerfilByInstituicao(id);

            // 200 ok
            if (list.size() > 0) {
                return list;
            }

            // 204 no content
            throw new NoContentException("Pet");
        }

        // 404 not found
        throw new EntityNotFoundException(id);
    }

    public PetPerfil createPet(
            MultipartFile multipart, 
            Integer instituicaoId,
            String nome,
            Boolean doente,
            Date dataNasc,
            String especie,
            String raca,
            String porte,
            String sexo,
            String descricao,
            List<Integer> caracteristicas
        ) {
        // creating pet
        PetRequest novoPet = new PetRequest(instituicaoId,
            nome,
            doente,
            dataNasc,
            especie,
            raca,
            porte,
            sexo,
            descricao,
            caracteristicas
        );
        Pet entity = createPet(novoPet);

        // uploading pet profile image
        try {
            String fileName = UploadFile.uploadFile(activeProfile, "img\\pets\\" + multipart.getOriginalFilename(), multipart);
            TimeUnit.SECONDS.sleep(1);
            entity.setCaminhoImagem(fileName);
        } catch(Exception ex) {
            throw new InvalidFieldException("file", ex.getMessage());
        }

        // 201 created
        return new PetPerfil(petRepository.save(entity));
    }

    // returns created pet
    private Pet createPet(PetRequest novoPet) {
        
        Optional<Instituicao> optionalInstituicao = instituicaoRepository.findById(novoPet.getInstituicaoId());

        if (optionalInstituicao.isPresent()) {
            Pet pet = fromPetRequestToPet(novoPet, optionalInstituicao.get());
            pet = petRepository.save(pet);

            for (Integer caracteristicaId : novoPet.getCaracteristicas()) {

                PetHasCaracteristica petHasCaracteristica = new PetHasCaracteristica();
                Optional<Caracteristica> caracteristica = caracteristicaRepositorio.findById(caracteristicaId);
                
                if(caracteristica.isPresent()) {
                    petHasCaracteristica.setCaracteristica(caracteristica.get());
                    petHasCaracteristica.setPet(pet);
                    petHasCaracteristica = petHasCaracteristicaRepository.save(petHasCaracteristica);
                }
            }

            // 201 created
            return pet;
        }

        // 404 instituicao not found
        throw new IdNotFoundException(novoPet.getInstituicaoId(), "instituicaoId");
    }

    private Pet fromPetRequestToPet(PetRequest novoPet, Instituicao instituicao) {
        Pet pet = new Pet();
        pet.setAdotado(false);
        pet.setCaminhoImagem("/");
        pet.setDataNasc(novoPet.getDataNasc());
        pet.setDescricao(novoPet.getDescricao());
        pet.setDoente(novoPet.getDoente());
        pet.setEspecie(novoPet.getEspecie());
        pet.setNome(novoPet.getNome());
        pet.setPorte(novoPet.getPorte());
        pet.setPetHasCaracteristica(List.of());
        pet.setSexo(novoPet.getSexo());
        pet.setRaca(novoPet.getRaca());
        pet.setInstituicao(instituicao);
        
        // falta lista de caracteristicas
        return pet;
    }

    public PremioDto postMimo(int id, MultipartFile multipart) {
        try {
            Pet pet = petRepository.findById(id).orElseThrow(() -> {
                throw new EntityNotFoundException(id);
            });
            String fileName = UploadFile.uploadFile(activeProfile, "img\\premios\\" + multipart.getOriginalFilename(), multipart);
            TimeUnit.SECONDS.sleep(1);
            Premio premio = new Premio(pet, fileName);
            PremioDto dto = new PremioDto(premioRepositorio.save(premio));
            return dto;
        } catch(Exception err) {
            throw new EntityNotFoundException(id);
        }
    }

    public PetsInstituicao getPetPerfilByInstituicaoIdHashTable(int id) {

        // 200
        if(instituicaoRepository.existsById(id)) {   
            List<Pet> listaPet = petRepository.findAll();
            HashTable hash = new HashTable(listaPet);
            PetsInstituicao filtred = hash.find(id);

            // 200
            if(filtred != null) {
                return filtred;
            }
            
            // 204 no content
            throw new NoContentException("pet");
        }

        // 404 not found
        throw new EntityNotFoundException(id);
    }

}
