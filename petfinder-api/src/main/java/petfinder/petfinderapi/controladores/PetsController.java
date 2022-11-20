package petfinder.petfinderapi.controladores;

import static org.springframework.http.ResponseEntity.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petfinder.petfinderapi.controladores.util.HeaderConfig;
import petfinder.petfinderapi.entidades.*;
import petfinder.petfinderapi.repositorios.*;
import petfinder.petfinderapi.resposta.Message;
import petfinder.petfinderapi.resposta.PetPerfil;
import petfinder.petfinderapi.resposta.PetPerfilEdicao;
import petfinder.petfinderapi.resposta.PremioDto;
import petfinder.petfinderapi.rest.ClienteCep;
import petfinder.petfinderapi.rest.DistanciaResposta;
import petfinder.petfinderapi.service.ServicePet;
import petfinder.petfinderapi.specifications.PetSpecification;
import petfinder.petfinderapi.utilitarios.FilaObj;
import petfinder.petfinderapi.utilitarios.PilhaObj;
import petfinder.petfinderapi.utilitarios.HashTable.HashTable;
import petfinder.petfinderapi.utilitarios.HashTable.PetsInstituicao;
import petfinder.petfinderapi.utilitarios.GerenciadorArquivos;
import petfinder.petfinderapi.utilitarios.ListaObj;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
@CrossOrigin
@Tag(name = "Pet",description = "API para controlar os pets, os prêmios e as caracteristicas")
public class PetsController implements GerenciadorArquivos {

    // repositories
    
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

    @Autowired
    private PetInstituicaoRepositorio view;

    public static List<Pet> pets = new ArrayList<>();
    public static List<Premio> premios = new ArrayList<>();
    private FilaObj<Object> filaObj = new FilaObj<Object>(10);

    // services
    @Autowired
    private ServicePet servicePet;

    @GetMapping("/view-test")
    public ResponseEntity<List<PetInstituicao>> getView() {
        return ok(view.findAll());
    }

    @PostMapping("/{id}/premios")
    public ResponseEntity<PremioDto> postMimo(@PathVariable int id, @RequestParam("file") MultipartFile multipart) {
        PremioDto res = servicePet.postMimo(id, multipart);
        return created(HeaderConfig.getLocation(res.getId())).body(res);
    }

    @GetMapping("/{id}/perfil")
    @Operation(description = "retorna dados do perfil do pet")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    public ResponseEntity<PetPerfil> getPetPerfil(@PathVariable Integer id, @RequestParam(required = false) Integer userId) {
        return ResponseEntity.ok(servicePet.getPetPerfil(id, userId));
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint que retorna um pet especifico pelo ID")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    ResponseEntity<PetPerfil> getPetById(@PathVariable int id, @RequestParam(required = false) Integer userId) {
        return ResponseEntity.ok(servicePet.getPetPerfil(id, userId));
    }

    @GetMapping
    @Operation(description = "Endpoint que retorna uma lista com todos os pets")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "204", description = "Not Content", content = @Content)
    public ResponseEntity<List<PetPerfil>> getPets() {
        List<PetPerfil> lista = repositoryPet.findAllPetPerfil();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/distinct")
    @Operation(description = "Endpoint que retorna uma lista de pets onde a especie tem que ser diferente")
    public ResponseEntity<List<String>> getPetsEspecie() {
        List<String> lista = repositoryPet.findDistinctByEspecie();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/instituicao/{id}")
    @Operation(description = "Endpoint que retorna uma lista de pets de uma instituição especifica")
    public ResponseEntity<List<PetPerfil>> getByInstituicaoId(@PathVariable int id) {
        return ResponseEntity.ok(servicePet.getPetPerfilByInstituicaoId(id));
    }

    @GetMapping("/instituicao/hashTable/{id}")
    @Operation(description = "Endpoint que retorna uma lista de pets de uma instituição utilizando HashTable")
    public ResponseEntity<PetsInstituicao> getPetPerfilByInstituicaoHashTable(@PathVariable int id) {
        return ok(servicePet.getPetPerfilByInstituicaoIdHashTable(id));
    }

    @GetMapping("/instituicao/pets/count/{id}")
    ResponseEntity countByPetInstituicao(@PathVariable int id) {
        int qtdPetInst = repositoryPet.findAllPetInstituicao(id);
        return ResponseEntity.status(200).body(qtdPetInst);
    }


    @PostMapping
    @Operation(description = "Endpoint para cadastro de um novo pet em uma instituição especifica")
    public ResponseEntity<PetPerfil> postPet(
        @RequestParam("file") MultipartFile multipart, 
        @RequestParam("instituicaoId") Integer instituicaoId,
        @RequestParam("nome") String nome,
        @RequestParam("doente") Boolean doente,
        @RequestParam("dataNasc") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataNasc,
        @RequestParam("especie") String especie,
        @RequestParam("raca") String raca,
        @RequestParam("porte") String porte,
        @RequestParam("sexo") String sexo,
        @RequestParam("descricao") String descricao,
        @RequestParam("caracteristicas[]") Integer[] caracteristicas
    ) {
        // creating pet
        PetPerfil petCriado = servicePet.createPet(
            multipart,
            instituicaoId,
            nome,
            doente,
            dataNasc,
            especie,
            raca,
            porte,
            sexo,
            descricao,
            List.of(caracteristicas)
        );
        // 201 created
        return ResponseEntity.created(HeaderConfig.getLocation(petCriado.getId())).body(petCriado);
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualizar informações de um pet especifico")
    public ResponseEntity<Void> putPet(@RequestBody PetPerfilEdicao petAtualizado, @PathVariable int id) {
        Pet petAtual = repositoryPet.getById(id);

        if (repositoryPet.existsById(id)) {
            petAtual.setDescricao(petAtualizado.getDescricao());
            petAtual.setEspecie(petAtualizado.getEspecie());
            petAtual.setDoente(petAtualizado.getIsDoente());
            petAtual.setNome(petAtualizado.getNome());
            petAtual.setPorte(petAtualizado.getPorte());
            petAtual.setRaca(petAtualizado.getRaca());

            repositoryPet.save(petAtual);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
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
            List<PetHasCaracteristica> caracteristicas = repositoryHasCaracteristica.findByPetId(id);

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

    // @PatchMapping(value = "/premio/foto/{id}", consumes = "image/jpeg")
    // @Operation(description = "Inserir imagem no premio")
    // public ResponseEntity<Object> patchFotoPremio(@PathVariable int id, @RequestBody byte[] novaFoto) {

    //     Premio premioEncontrado = repositoryPremio.getById(id);
    //     premioEncontrado.setImg(novaFoto);
    //     repositoryPremio.save(premioEncontrado);

    //     return ResponseEntity.status(200).build();
    // }

    @GetMapping(value = "/premio/foto/{codigo}", produces = "image/jpeg")
    @Operation(description = "Endpoint pegar foto do premio por id do premio")
    public ResponseEntity<String> getFotoPremio(@PathVariable int codigo) {
        if (!repositoryPremio.existsById(codigo)){
            return ResponseEntity.status(404).build();
        }
        Premio premioEncontrado = repositoryPremio.getById(codigo);
        return ResponseEntity.status(200).body(premioEncontrado.getImg());
    }

    @PostMapping("/premio")
    @Operation(description = "Endpoint para cadastrar um novo premio")
    public ResponseEntity<Object> postPremio(
            @RequestBody @Valid Premio novoPremio) {

        if (Objects.nonNull(novoPremio)) {
            repositoryPremio.save(novoPremio);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/premio/{id}")
    @Operation(description = "Endpoint que atualiza um premio especifico filtrado pelo ID")
    public ResponseEntity<Object> putPremio(@RequestBody Premio premioAtualizado, @PathVariable int id) {
        if (repositoryPremio.existsById(id)) {
            premioAtualizado.setId(id);
            repositoryPremio.save(premioAtualizado);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/static/img/premios")
    @Operation(description = "Endpoint que retorna uma lista de todos os premios")
    public ResponseEntity<Object> getPremios() {
        List<Premio> lista = repositoryPremio.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/premio/{id}")
    @Operation(description = "Endpoint que retorna um premio filtrado pelo ID")
    ResponseEntity<Object> getByidPremios(@PathVariable int id) {
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
            List<PetHasCaracteristica> relation = repositoryHasCaracteristica.findByPetId(id);

            for (PetHasCaracteristica phc : relation) {
                caracteristicas.add(phc.getCaracteristica());
            }

            return ResponseEntity.status(200).body(caracteristicas);
        }

        // pet não encontrado
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/premio/{id}")
    @Operation(description = "Endpoint que deleta um premio especifico filtrado pelo ID")
    ResponseEntity<Object> deleteByIdPremio(@PathVariable int id) {
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
    public ResponseEntity<Object> postCaracteristica(
            @RequestBody @Valid Caracteristica novaCaracteristica) {
        if (Objects.nonNull(novaCaracteristica)) {
            repositoryCaracteristica.save(novaCaracteristica);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/caracteristica/{id}")
    @Operation(description = "Endpoint para atualizar uma caracteristica especifica filtrada pelo ID ")
    public ResponseEntity<Object> putCaracteristica(@RequestBody Caracteristica caracteristicaAtualizada, @PathVariable int id) {
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
    ResponseEntity<Object> deleteByIdCaracteristica(@PathVariable int id) {
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
                relation.setPet(pet.get());
                relation.setCaracteristica(caracteristicasValidas.peek());

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
    public ResponseEntity<Object> getHasCaracteristica() {
        List<PetHasCaracteristica> lista = repositoryHasCaracteristica.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @PutMapping("/has-caracteristica/{indice}")
    @Operation(description = "Endpoint para atualização do relacionamente de uma caracteristica")
    public ResponseEntity<Object> putHasCaracteristica(@RequestBody PetHasCaracteristica hasCaracteristicaAtualizada,
                                               @PathVariable int indice) {
        if (repositoryHasCaracteristica.existsById(indice)) {
            hasCaracteristicaAtualizada.setId(indice);
            repositoryHasCaracteristica.save(hasCaracteristicaAtualizada);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }


    @GetMapping("/distancias/{cepUsuario}/{distanciaMax}")
    public ResponseEntity<Object> getListaDistanciasPet(@PathVariable String cepUsuario,
                                                          @PathVariable Integer distanciaMax) {
        List<Pet> lista = repositoryPet.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        for (int i = 0; i < lista.size(); i++) {
            DistanciaResposta resposta = clienteCep.getDistancia(cepUsuario,
                    lista.get(i).getInstituicao().getEndereco().getCep());
            Integer distancia = resposta.getDistancia();

            if (distancia <= distanciaMax) {
                filaObj.insert(lista.get(i));
            }
        }

        return ResponseEntity.status(200).body(filaObj.getFila());
    }

    @GetMapping("/premios-instituicao/{idInstituicao}")
    @Operation(description = "Endpoint para retornar todos os mimos de determinada instituição")
    public ResponseEntity<Object> getByMimosInstituicao(@PathVariable int idInstituicao) {

        List<Pet> listaPet = repositoryPet.findByInstituicaoId(idInstituicao);

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
    public ResponseEntity<Object> getByMimosEspecie(@PathVariable String especie) {
        List<PetPerfil> listaPet = repositoryPet.findByEspecieIgnoreCase(especie);

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

    @GetMapping("/static/img/premios/{idPet}")
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
    public ResponseEntity<Object> getByCaracteristicasPet(@PathVariable int idCaracteristica) {
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
        String nome, dataNasc, especie, raca, porte, descricao, sexo, caracteristica;
        Boolean adotado, doente;
        int contaRegCorpoLido = 0;
        int qtdRegCorpoGravado;

        List<Pet> listaLida = new ArrayList<>();
        List<Caracteristica> listaLida2 = new ArrayList<>();

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

                    Pet pet = new Pet(nome,new Date(),especie,raca,porte,sexo,descricao,doente,adotado,instituicao);

                    repositoryPet.save(pet);

                    listaLida.add(pet);
                }
                else if (tipoRegistro.equals("03")) {
                    System.out.println("É um registro do segundo corpo");
                    caracteristica = registro.substring(2,20).trim();
                    contaRegCorpoLido++;

                    Caracteristica c = new Caracteristica(caracteristica);

                    repositoryCaracteristica.save(c);

                    listaLida2.add(c);
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

    @Override
    public void gravaRegistro(String registro, String nomeArq) {
    }

    @Override
    public <T> T gravaArquivoTxt(List<Demanda> listaDemanda, List<Usuario> listaUsuario, List<Instituicao> listaInstituicao, List<Pet> listaPet, String nomeArq) {
        return null;
    }

    @PostMapping(value = "/import-pet/{idInstituicao}", consumes = "multipart/form-data")
    public ResponseEntity postNovoPet(@RequestBody MultipartFile novoPet, @PathVariable int idInstituicao) {
        String nomeArq = novoPet.getResource().getFilename();
        Instituicao instituicao = repositoryInstituicao.getById(idInstituicao);
        if (leArquivoTxt(nomeArq, instituicao)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/doentes/{qtdPets}")
    public ResponseEntity<List<PetPerfil>> getPetsDoentes(@PathVariable int qtdPets) {
        List<PetPerfil> petsDoentes = repositoryPet.findByDoenteAndAdotado();

        if (petsDoentes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<PetPerfil> listaPet = new ArrayList<>();
        for (int i = 0; i < qtdPets && i < petsDoentes.size(); i++) {
            listaPet.add(petsDoentes.get(i));
        }
        return ResponseEntity.status(200).body(listaPet);
    }

    @GetMapping("/premios/get/{idPet}")
    public ResponseEntity getPremiosByPetId(@PathVariable int idPet) {
        List<PremioDto> premios = repositoryPremio.findByPetIdDto(idPet);
        if (premios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(premios);
    }

    @GetMapping("/apadrinhamentos/usuario/{idUser}")
    public ResponseEntity getPetsApadrinhadosPorUser(@PathVariable int idUser) {
        Date now = new Date();
        Date thirtyDaysBefore = new Date();
        thirtyDaysBefore.setDate(thirtyDaysBefore.getDate() - 30);

        List<PetPerfil> pets = repositoryPet.findPetByDemandaApadrinhamentoAndUsuario(idUser, thirtyDaysBefore);
        if (pets.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(pets);
    }

    @GetMapping("/qtd/{qtdPets}")
    public ResponseEntity<List<PetPerfil>> getPetsQtdPets(@PathVariable int qtdPets) {
        List<PetPerfil> pets = repositoryPet.findByAdotado();

        if (pets.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<PetPerfil> listaPet = new ArrayList<>();
        for (int i = 0; i < qtdPets && i < pets.size(); i++) {
            listaPet.add(pets.get(i));
        }
        return ResponseEntity.status(200).body(listaPet);
    }

    @GetMapping("/userPreferences/{idUser}/{limit}")
    public ResponseEntity<List<PetPerfil>> getPetsWithUserPreferences(@PathVariable int idUser, @PathVariable int limit){
        List<PetPerfil> pets = repositoryPet.findByUserPreferences(idUser);
        if(limit == 999){
            pets.addAll(repositoryPet.findNotByUserPreferences(idUser));
        }
        else if(pets.size() < limit){
            pets.addAll(repositoryPet.findNotByUserPreferences(idUser).subList(0, limit-pets.size()));
        }

        return ResponseEntity.status(200).body(pets);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PetPerfil>> getPetsByFilters(){
        List<String> porte = new ArrayList<String>();
        porte.add(0, "Grande");
        porte.add(1, "Pequeno");
        
        Specification<PetPerfil> spec = Specification.where(porte != null ? PetSpecification.porteIn(porte) : null);

        return ResponseEntity.status(200).body(repositoryPet.findAll(spec));
    }
}