package petfinder.petfinderapi.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.PetHasCaracteristica;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.CaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.PetHasCaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.PetRequest;
import petfinder.petfinderapi.resposta.PetPerfil;
import petfinder.petfinderapi.rest.DistanciaResposta;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.IdNotFoundException;
import petfinder.petfinderapi.service.exceptions.NoContentException;

@Service
public class ServicePet {
    
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

    // returns created pet
    public PetPerfil createPet(PetRequest novoPet) {
        
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
            return new PetPerfil(pet);
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

}
