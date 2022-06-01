package petfinder.petfinderapi.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petfinder.petfinderapi.entidades.*;
import petfinder.petfinderapi.repositorios.*;
import petfinder.petfinderapi.resposta.Message;
import petfinder.petfinderapi.rest.ClienteCep;
import petfinder.petfinderapi.rest.DistanciaResposta;
import petfinder.petfinderapi.utilitarios.FilaObj;
import petfinder.petfinderapi.utilitarios.PilhaObj;
import petfinder.petfinderapi.utilitarios.GerenciadorArquivos;
import petfinder.petfinderapi.utilitarios.ListaObj;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
@Tag(name = "Pet",description = "API para controlar os pets, os prêmios e as caracteristicas")
public class PetsController implements GerenciadorArquivos {

    @Autowired
    private PetRepositorio repositoryPet;

    @Autowired
    private PremioRepositorio repositoryPremio;

    @Autowired
    private CaracteristicaRepositorio repositoryCaracteristica;

    @Autowired
    private PetHasCaracteristicaRepositorio repositoryHasCaracteristica;

    @Autowired
    private ClienteCep clienteCep;

    @Autowired
    private InstituicaoRepositorio repositoryInstituicao;

    public static List<Pet> pets = new ArrayList<>();
    public static List<Premio> premios = new ArrayList<>();
    private FilaObj filaObj = new FilaObj<>(10);

    @PatchMapping(value = "/foto/{id}", consumes = "image/jpeg")
    @Operation(description = "EndPoint para cadastrar a foto de perfil do animal")
    public ResponseEntity patchFoto(@PathVariable int id, @RequestBody byte[] novaFoto) {

        Pet petEncontrado = repositoryPet.getById(id);
        petEncontrado.setFotoPerfil(novaFoto);
        repositoryPet.save(petEncontrado);

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/foto/{codigo}", produces = "image/jpeg")
    @Operation(description = "EndPoint para ver as fotos dos animais")
    public ResponseEntity<byte[]> getFoto(@PathVariable int codigo) {
        if (!repositoryPet.existsById(codigo)){
            return ResponseEntity.status(404).build();
        }
        Pet petEncontrado = repositoryPet.getById(codigo);
        return ResponseEntity.status(200).body(petEncontrado.getFotoPerfil());
    }

    @PostMapping
    @Operation(description = "Endpoint para cadastro de um novo pet em uma instituição especifica")
    public ResponseEntity<Pet> postPet(@RequestBody @Valid Pet novoPet) {

        Optional<Instituicao> instituicao = repositoryInstituicao.findById(novoPet.getFkInstituicao().getId());

        if (instituicao.isPresent()) {
            novoPet.setFkInstituicao(instituicao.get());
            novoPet.setAdotado(false);
            novoPet = repositoryPet.save(novoPet);
            return ResponseEntity.status(201).body(novoPet);
        }
        
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualizar informações de um pet especifico")
    public ResponseEntity putPet(@RequestBody Pet petAtualizado, @PathVariable int id) {
        if (repositoryPet.existsById(id)) {
            petAtualizado.setId(id);
            repositoryPet.save(petAtualizado);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping
    @Operation(description = "Endpoint que retorna uma lista com todos os pets")
    public ResponseEntity getPets() {
        List<Pet> lista = repositoryPet.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint que retorna um pet especifico pelo ID")
    ResponseEntity<Pet> getPetById(@PathVariable int id) {

        Optional<Pet> pet = repositoryPet.findById(id);

        if (pet.isPresent()) {
            return ResponseEntity.status(200).body(pet.get());
        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/instituicao/{id}")
    @Operation(description = "Endpoint que retorna uma lista de pets de uma instituição especifica")
    ResponseEntity getByInstiuicaoId(@PathVariable int id) {
        if (repositoryPet.existsById(id)) {
            List<Pet> lista = repositoryPet.findByFkInstituicaoId(id);

            if (lista.isEmpty()) {
                return ResponseEntity.status(404).body(new Message("Instituição ainda não possui pets"));
            }
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Muda valor de 'adotado' para true")
    ResponseEntity<Pet> deletePet(@PathVariable int id) {
        
        if (repositoryPet.existsById(id)) {

            Pet pet = repositoryPet.findById(id).get();

            pet.setAdotado(true);
            repositoryPet.save(pet);

            // 200 deleted pet
            return ResponseEntity.status(200).body(pet);
        }

        // 404 usuário não encontrado
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/permanent/{id}")
    @Operation(description = "Endpoint que deleta um pet especifico e seus premios pelo ID")
    ResponseEntity<Pet> deletePermanentByIdpet(@PathVariable int id) {  
        
        if (repositoryPet.existsById(id)) {

            Pet pet = repositoryPet.findById(id).get();
            List<Premio> premios = repositoryPremio.findByPetId(pet.getId());
            List<PetHasCaracteristica> caracteristicas = repositoryHasCaracteristica.findByFkPetId(id);

            for (PetHasCaracteristica phc : caracteristicas) {
                repositoryHasCaracteristica.deleteById(phc.getId());
            }

            for (Premio p : premios) {
                repositoryPremio.deleteById(p.getId());
            }

            repositoryPet.deleteById(id);
            return ResponseEntity.status(200).body(pet);
        }

        return ResponseEntity.status(404).build();
    }

    // ================================================= //
    // Premios
    // ================================================= //

    @PatchMapping(value = "/premio/foto/{id}", consumes = "image/jpeg")
    @Operation(description = "Inserir imagem no premio")
    public ResponseEntity patchFotoPremio(@PathVariable int id, @RequestBody byte[] novaFoto) {

        Premio premioEncontrado = repositoryPremio.getById(id);
        premioEncontrado.setImg(novaFoto);
        repositoryPremio.save(premioEncontrado);

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/premio/foto/{codigo}", produces = "image/jpeg")
    @Operation(description = "Endpoint pegar foto do premio por id do premio")
    public ResponseEntity<byte[]> getFotoPremio(@PathVariable int codigo) {
        if (!repositoryPremio.existsById(codigo)){
            return ResponseEntity.status(404).build();
        }
        Premio premioEncontrado = repositoryPremio.getById(codigo);
        return ResponseEntity.status(200).body(premioEncontrado.getImg());
    }

    @PostMapping("/premio")
    @Operation(description = "Endpoint para cadastrar um novo premio")
    public ResponseEntity postPremio(
            @RequestBody @Valid Premio novoPremio) {

        if (Objects.nonNull(novoPremio)) {
            repositoryPremio.save(novoPremio);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/premio/{id}")
    @Operation(description = "Endpoint que atualiza um premio especifico filtrado pelo ID")
    public ResponseEntity putPremio(@RequestBody Premio premioAtualizado, @PathVariable int id) {
        if (repositoryPremio.existsById(id)) {
            premioAtualizado.setId(id);
            repositoryPremio.save(premioAtualizado);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/premios")
    @Operation(description = "Endpoint que retorna uma lista de todos os premios")
    public ResponseEntity getPremios() {
        List<Premio> lista = repositoryPremio.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/premio/{id}")
    @Operation(description = "Endpoint que retorna um premio filtrado pelo ID")
    ResponseEntity getByidPremios(@PathVariable int id) {
        if (repositoryPremio.existsById(id)) {
            List<Premio> lista = repositoryPremio.findById(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/caracteristicas/pet/{id}")
    @Operation(description = "Endpoint que retorna um premio filtrado pelo ID")
    ResponseEntity<List<Caracteristica>> getCaracteristicasByPetId(@PathVariable int id) {

        Optional<Pet> pet = repositoryPet.findById(id);

        // pet encontrado
        if (pet.isPresent()) {
            List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
            List<PetHasCaracteristica> relation = repositoryHasCaracteristica.findByFkPetId(id);

            for (PetHasCaracteristica phc : relation) {
                caracteristicas.add(phc.getFkCaracteristica());
            }

            return ResponseEntity.status(200).body(caracteristicas);
        }

        // pet não encontrado
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/premio/{id}")
    @Operation(description = "Endpoint que deleta um premio especifico filtrado pelo ID")
    ResponseEntity deleteByIdPremio(@PathVariable int id) {
        if (repositoryPremio.existsById(id)) {
            repositoryPremio.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    // ================================================= //
    // Caracteristicas
    // ================================================= //

    @PostMapping("/caracteristica")
    @Operation(description = "Endpoint para cadastrar uma caracteristica")
    public ResponseEntity postCaracteristica(
            @RequestBody @Valid Caracteristica novaCaracteristica) {
        if (Objects.nonNull(novaCaracteristica)) {
            repositoryCaracteristica.save(novaCaracteristica);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/caracteristica/{id}")
    @Operation(description = "Endpoint para atualizar uma caracteristica especifica filtrada pelo ID ")
    public ResponseEntity putCaracteristica(@RequestBody Caracteristica caracteristicaAtualizada, @PathVariable int id) {
        if (repositoryCaracteristica.existsById(id)) {
            caracteristicaAtualizada.setId(id);
            repositoryCaracteristica.save(caracteristicaAtualizada);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/caracteristicas")
    @Operation(description = "Endpoint que retorna uma lista com todas as caracteristicas")
    public ResponseEntity<List<Caracteristica>> getCaracteristica() {
        List<Caracteristica> lista = repositoryCaracteristica.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/caracteristica/{id}")
    @Operation(description = "Endpoint que retorna uma caracteristica especifica filtrada pelo ID")
    ResponseEntity<Caracteristica> getByidCaracteristca(@PathVariable int id) {
        if (repositoryCaracteristica.existsById(id)) {
            Optional<Caracteristica> caracteristica = repositoryCaracteristica.findById(id);
            return ResponseEntity.status(200).body(caracteristica.get());
        }
        return ResponseEntity.status(404).build();
    }


    @DeleteMapping("/caracteristica/{id}")
    @Operation(description = "Endpoint que deleta uma caracteristica especifica filtrada pelo ID")
    ResponseEntity deleteByIdCaracteristica(@PathVariable int id) {
        if (repositoryCaracteristica.existsById(id)) {
            repositoryCaracteristica.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    // ================================================= //
    // Pet Has Caracteristicas
    // ================================================= //
    // !! REVISAR ESSA DESCRIÇÃO
    @PostMapping("/has-caracteristica/{idPet}")
    @Operation(description = "Endpoint para cadastrar um relacionamento de uma caracteristica")
    public ResponseEntity<List<Caracteristica>> postHasCaracteristica(@RequestBody List<Caracteristica> caracteristicas, @PathVariable int idPet) {

        Optional<Pet> pet = repositoryPet.findById(idPet); 

        // pet encontrado
        if (pet.isPresent()) {

            PilhaObj<Caracteristica> caracteristicasValidas = new PilhaObj<Caracteristica>(caracteristicas.size()); 
            List<Caracteristica> caracteristicasRegistradas = new ArrayList<Caracteristica>();

            // validando caracteristicas
            for (Caracteristica c : caracteristicas) {
                Optional<Caracteristica> caracteristica = repositoryCaracteristica.findById(c.getId());

                if(caracteristica.isPresent()) {
                    // verifica se pet já tem aquela caracteristica
                    caracteristicasValidas.push(c);
                }
            }

            // salvando relacionamento no banco
            while (caracteristicasValidas.isNotEmpty()) {
                PetHasCaracteristica relation = new PetHasCaracteristica();
                relation.setFkPet(pet.get());
                relation.setFkCaracteristica(caracteristicasValidas.peek());

                repositoryHasCaracteristica.save(relation);
                caracteristicasRegistradas.add(caracteristicasValidas.pop());
            }

            // 204 no content (nenhuma caracteristica foi válida)
            if (caracteristicasRegistradas.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            // retorna todas as caracteristicas cadastradas
            return ResponseEntity.status(200).body(caracteristicasRegistradas);    

        }

        // 404 pet não encontrado
        return ResponseEntity.status(404).build();
        
    }

    @GetMapping("/has-caracteristicas")
    @Operation(description = "Endpoint para retornar todos os registros de relacionamentos")
    public ResponseEntity getHasCaracteristica() {
        List<PetHasCaracteristica> lista = repositoryHasCaracteristica.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @PutMapping("/has-caracteristica/{indice}")
    @Operation(description = "Endpoint para atualização do relacionamente de uma caracteristica")
    public ResponseEntity putHasCaracteristica(@RequestBody PetHasCaracteristica hasCaracteristicaAtualizada,
                                               @PathVariable int indice) {
        if (repositoryHasCaracteristica.existsById(indice)) {
            hasCaracteristicaAtualizada.setId(indice);
            repositoryHasCaracteristica.save(hasCaracteristicaAtualizada);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }


    @GetMapping("/distancias/{cepUsuario}/{distanciaMax}")
    public ResponseEntity getListaDistanciasPet(@PathVariable String cepUsuario,
                                                          @PathVariable Integer distanciaMax) {
        List<Pet> lista = repositoryPet.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        for (int i = 0; i < lista.size(); i++) {
            DistanciaResposta resposta = clienteCep.getDistancia(cepUsuario,
                    lista.get(i).getFkInstituicao().getEndereco().getCep());
            Integer distancia = resposta.getDistancia();

            if (distancia <= distanciaMax) {
                filaObj.insert(lista.get(i));
            }
        }

        return ResponseEntity.status(200).body(filaObj.getFila());
    }

    @GetMapping("/premios-instituicao/{idInstituicao}")
    @Operation(description = "Endpoint para retornar todos os todos os mimos de determinada instituição")
    public ResponseEntity getByMimosInstituicao(@PathVariable int idInstituicao) {

        List<Pet> listaPet = repositoryPet.findByFkInstituicaoId(idInstituicao);

        for (int i = 0; i < listaPet.size(); i++) {
            Integer idPet = listaPet.get(i).getId();
            List<Premio> listaPremio = repositoryPremio.findByPetId(idPet);

            for (int j = 0; j < listaPremio.size(); j++) {
                if (listaPet.get(i).getId() == listaPremio.get(j).getId()) {
                    premios.add(i, listaPremio.get(j));
                }
            }
        }

        if (listaPet.isEmpty()) {
            return ResponseEntity.status(404).body(new Message("Ainda não temos animais dessa com mimos cadastrados" +
                    " nessa instituição"));
        }
        return ResponseEntity.status(200).body(premios);
    }

    @GetMapping("/premios-especie/{especie}")
    @Operation(description = "Endpoint para retornar todos os mimos de determinada espécie")
    public ResponseEntity getByMimosEspecie(@PathVariable String especie) {
        List<Pet> listaPet = repositoryPet.findByEspecieIgnoreCase(especie);

        for (int i = 0; i < listaPet.size(); i++) {
            Integer idPet = listaPet.get(i).getId();

            List<Premio> listaPremio = repositoryPremio.findByPetId(idPet);

            for (int j = 0; j < listaPremio.size(); j++) {
                premios.add(i, listaPremio.get(j));
            }
        }

        if (listaPet.isEmpty()) {
            return ResponseEntity.status(404).body(new Message("Ainda não temos animais dessa espécie com mimos " +
                    "cadastrados"));
        }
        return ResponseEntity.status(200).body(premios);
    }

    @GetMapping("/premios/{idPet}")
    @Operation(description = "Endpoint para retornar todos os mimos de determinado pet")
    public ResponseEntity<List<Premio>> getByMimosPet(@PathVariable int idPet) {
        Optional<Pet> pet = repositoryPet.findById(idPet);

        if (pet.isPresent()) {
            Integer id = pet.get().getId();

            List<Premio> premios = repositoryPremio.findByPetId(id);
            
            if (premios.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            return ResponseEntity.status(200).body(premios);
        }

        return ResponseEntity.status(404).body(premios);
    }

    @GetMapping("/caracteristicas/{idCaracteristica}")
    @Operation(description = "Endpoint para retornar uma lista de pets com determinada caracteristica")
    public ResponseEntity getByCaracteristicasPet(@PathVariable int idCaracteristica) {
        List<PetHasCaracteristica> lista = repositoryHasCaracteristica.findByFkCaracteriticaId(idCaracteristica);

        if (lista.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(lista);
    }

    @Override
    public void gravaArquivoCSV(ListaObj lista, String nomeArquivo) {
    }

    @Override
    public String leArquivoCSV(String nomeArq) {
        return null;
    }

    @Override
    public boolean leArquivoTxt(String nomeArq, Instituicao instituicao) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, dataNasc, especie, raca, porte, descricao, sexo;
        Boolean adotado, doente;
        int contaRegCorpoLido = 0;
        int qtdRegCorpoGravado;

        List<Pet> listaLida = new ArrayList<>();

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
        }

        try {
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0,2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2,6));
                    System.out.println("Ano e semestre: " + registro.substring(6,11));
                    System.out.println("Data e hora da gravação: " + registro.substring(11,30));
                    System.out.println("Versão do documento: " + registro.substring(30,33));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("É um registro de trailer");
                    qtdRegCorpoGravado = Integer.parseInt(registro.substring(2,5));
                    if (contaRegCorpoLido == qtdRegCorpoGravado) {
                        System.out.println("Quantidade de registros lidos é compatível " +
                                "com a quantidade de registros gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros lidos não é compatível " +
                                "com a quantidade de registros gravados");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de corpo");
                    nome = registro.substring(2,32).trim();
                    dataNasc = registro.substring(32, 42).trim();
                    especie = registro.substring(42,72).trim();
                    raca = registro.substring(72,102).trim();
                    porte = registro.substring(102,122).trim();
                    sexo = registro.substring(122,127).trim();
                    descricao = registro.substring(127,377).trim();
                    doente = Boolean.valueOf(registro.substring(378,383));
                    adotado = Boolean.valueOf(registro.substring(384,389));
                    contaRegCorpoLido++;

                    Pet pet = new Pet(nome,dataNasc,especie,raca,porte,sexo,descricao,doente,adotado,instituicao);

                    repositoryPet.save(pet);

                    listaLida.add(pet);
                }
                else {
                    System.out.println("Tipo de registro inválido!");
                }

                registro = entrada.readLine();
            }

            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo: " + erro);
            return false;
        }

        repositoryPet.saveAll(listaLida);

        System.out.println("\nConteúdo da lista lida:");
        for (Pet pet : listaLida) {
            System.out.println(pet);
        }

        return true;
    }

    @PostMapping(value = "/import-pet/{idInstituicao}", consumes = "multipart/form-data")
    public ResponseEntity postNovoUsuario(@RequestBody MultipartFile novoPet, @PathVariable int idInstituicao) {
        String nomeArq = novoPet.getResource().getFilename();
        Instituicao instituicao = repositoryInstituicao.getById(idInstituicao);
        if (leArquivoTxt(nomeArq, instituicao)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/doentes/{qtdPets}")
    public ResponseEntity getPetsDoentes(@PathVariable int qtdPets) {
        List<Pet> petsDoentes = repositoryPet.findByDoenteAndAdotado();

        if (petsDoentes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<Pet> listaPet = new ArrayList<>();
        for (int i = 0; i < qtdPets; i++) {
            listaPet.add(petsDoentes.get(i));
        }
        return ResponseEntity.status(200).body(listaPet);
    }
}
