package petfinder.petfinderapi.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.controladores.util.HeaderConfig;
import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.EnderecoRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.DtoAdmRequest;
import petfinder.petfinderapi.rest.ClienteCep;
import petfinder.petfinderapi.rest.Distancep;
import petfinder.petfinderapi.rest.DistanciaResposta;
import petfinder.petfinderapi.service.ServiceInstituicao;
import petfinder.petfinderapi.service.ServiceRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/instituicoes")
@CrossOrigin
@Tag(name = "Instituições e Endereço", description = "Essa API é reponsável por fazer requisições da instituição e do endereço")
public class InsitituicoesController {

    // repositorios
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ClienteCep clienteCep;

    @Autowired
    private ServiceInstituicao service;

    @Autowired
    private ServiceRequest serviceRequest;

    // endpoints

    // retorna todas as instituições
    @GetMapping
    @Operation(description = "Endpoint que retorna uma lista de todas as suas instituições")
    public ResponseEntity<List<Instituicao>> listarInstituicoes(){
        serviceRequest.saveRequest();

        List<Instituicao> instituicoes = instituicaoRepositorio.findAll();

        // verifica se lista de instituições está vazia
        if(instituicoes.isEmpty()) {

            // 204 no content
            return ResponseEntity.status(204).body(instituicoes);
        }

        // 200
        return ResponseEntity.status(200).body(instituicoes);
    }

    // retorna instituicao baseada no ID
    @GetMapping("/{id}")
    @Operation(description = "Enpoint que retorna uma unica instituição filtrada pelo seu ID")
    public ResponseEntity<Instituicao> getInstituicaoById(@PathVariable int id) {
        serviceRequest.saveRequest();

        Optional<Instituicao> instituicao = instituicaoRepositorio.findById(id);

        // verificando se instituicao existe
        if(instituicao.isPresent()) {

            // 200
            return ResponseEntity.status(200).body(instituicao.get());
        }

        // 404 - instituicao não encontrada
        return ResponseEntity.status(404).build();
    }

    // cadastra instituicao
    @PostMapping
    @Operation(description = "Endpoint para cadastro de instituição, juntamente com seu endereço e primeiro usuário admin")
    public ResponseEntity<Usuario> postInstituicao(@RequestBody @Valid DtoAdmRequest req){
        serviceRequest.saveRequest();
        Usuario body = service.createInstituicao(req);
        return ResponseEntity.created(HeaderConfig.getLocation(body.getId())).body(body);
    }

    // edita dados da instituicao
    @PutMapping("/{id}")
    @Operation(description = "Endpoint para edição das informações da instituição")
    public ResponseEntity<Object> putInstituicao(@RequestBody @Valid Instituicao instituicaoAtualizada, @PathVariable int id){
        serviceRequest.saveRequest();

        // verificando se instituicao existe
        if(instituicaoRepositorio.existsById(id)){
            instituicaoAtualizada.setId(id);
            instituicaoRepositorio.save(instituicaoAtualizada);

            // 200
            return ResponseEntity.status(200).build();
        }

        // 404 - instituicao não encontrada
        return ResponseEntity.status(404).build();
    }

    // ENDPOINTS ENDEREÇO

    // retorna todos os enderecos
    @GetMapping("/endereco")
    @Operation(description = "Endpoint que retorna uma lista com todos os endereços")
    public ResponseEntity<List<Endereco>> getAllEndereco() {
        serviceRequest.saveRequest();

        List<Endereco> enderecos = enderecoRepositorio.findAll();

        // verifica se lista de endereços está vazia
        if (enderecos.isEmpty()) {

            // 204
            return ResponseEntity.status(204).body(enderecos);
        } 

        // 200
        return ResponseEntity.status(200).body(enderecos);
    }

    // retorna endereco específico
    @GetMapping("/endereco/{id}")
    @Operation(description = "Endpoint que retorna um endereço especifico pelo seu ID")
    public ResponseEntity<Endereco> getEndereco(@PathVariable int id) {
        serviceRequest.saveRequest();
        Optional<Endereco> endereco = enderecoRepositorio.findById(id);

        // verifica se endereço é válido
        if(endereco.isPresent()) {

            // 200
            return ResponseEntity.status(200).body(endereco.get());
        }

        // 404 endereço não encontrado
        return ResponseEntity.status(404).build();
    }

    // cadastra endereco
    @PostMapping("/endereco")
    @Operation(description = "Endpoint para cadastro de Endereço")
    public ResponseEntity<Object> postEndereco(@RequestBody @Valid Endereco endereco) {
        serviceRequest.saveRequest();
        enderecoRepositorio.save(endereco);

        // 201
        return ResponseEntity.status(201).build();
    }

    // edita um endereco especifico
    @PutMapping("/endereco/{id}")
    @Operation(description = "Endpoint para edição de um endereço filtrado pelo ID")
    public ResponseEntity<Object> putEndereco(@RequestBody @Valid Endereco endereco, @PathVariable int id) {
        serviceRequest.saveRequest();

        // verificando se endereço existe
        if (enderecoRepositorio.findById(id).isPresent()) {
            
            // atualizando endereço
            endereco.setId(id);
            enderecoRepositorio.save(endereco);

            // 200
            return ResponseEntity.status(200).build();
        }

        // 404 endereço não encontrado
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/distancia/{cepUsuario}/{cepInstituicao}")
    @Operation(description = "Endpoint para obter a distância entre o Usuário e a Instituição")
    public ResponseEntity getDistancia(@PathVariable String cepUsuario,
                               @PathVariable String cepInstituicao) {
        serviceRequest.saveRequest();

        DistanciaResposta clienteDistancia = clienteCep.getDistancia(cepUsuario, cepInstituicao);

        if (clienteDistancia != null) {
            return ResponseEntity.status(200).body(clienteDistancia.getDistancia());
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/distancias/{cepUsuario}/{distanciaMax}")
    public ResponseEntity<List<Instituicao>> getListaDistanciasInstituicaoes(
        @PathVariable String cepUsuario,
        @PathVariable Integer distanciaMax) {
        
        serviceRequest.saveRequest();

        // listas
        List<Instituicao> lista = instituicaoRepositorio.findAll();
        List<Instituicao> instituicoesProximas = new ArrayList<Instituicao>();

        // 204 sem instituições cadastradas
        if (lista.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        // percorrendo todas as instituições para medir sua distância com o usuário
        for (Instituicao instituicao : lista) {
            String cepInstituicao = instituicao.getEndereco().getCep();

            // usuário tem o mesmo cep da instituição
            if (cepUsuario.equals(cepInstituicao)) {
                instituicoesProximas.add(instituicao);
                continue;
            } 

            // pegando distancia entre usuário e instituição
            ResponseEntity<Distancep> res = clienteCep.getDistancep(cepUsuario, cepInstituicao);

            // verificando se requisição foi realizada com sucesso (STATUS OK 200)
            // verificando se distancia entre usuário e instituicao é menor que a distancia máxima
            if (res.getStatusCodeValue() == 200 && res.getBody().getDistance() <= distanciaMax) {
                // add inst
                instituicoesProximas.add(instituicao);
                continue;
            } 
        }
        
        // 200 ok
        return ResponseEntity.status(200).body(instituicoesProximas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstituicao(@PathVariable int id) {

        serviceRequest.saveRequest();

        // instituicao existe
        if (instituicaoRepositorio.existsById(id)) {
            Instituicao instituicao = instituicaoRepositorio.findById(id).get();
            int idEndereco = instituicao.getEndereco().getId();
            int adminId = usuarioRepositorio.findByInstituicaoId(id).getId();

            // deleting instituicao
            usuarioRepositorio.deleteById(adminId);
            instituicaoRepositorio.deleteById(id);
            enderecoRepositorio.deleteById(idEndereco);

            return ResponseEntity.noContent().build();
        }

        // 404 instituicao não encontrada
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/instituicao/colaborador/count/{id}")
    ResponseEntity countByPetInstituicao(@PathVariable int id) {
        serviceRequest.saveRequest();
        int qtdIntituicaoInst = instituicaoRepositorio.findAllColaboradoresInstituicao(id);
        return ResponseEntity.status(200).body(qtdIntituicaoInst);
    }

    @GetMapping("/apadrinhamentos/usuario/{idUser}")
    public ResponseEntity getPetsApadrinhadosPorUser(@PathVariable int idUser) {
        serviceRequest.saveRequest();
        List<Instituicao> instituicao = instituicaoRepositorio.findInstituicaoByDemandaApadrinhamentoAndUsuario(idUser);
        if (instituicao.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(instituicao);
    }
}
