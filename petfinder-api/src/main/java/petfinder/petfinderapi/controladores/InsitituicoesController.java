package petfinder.petfinderapi.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.repositorios.EnderecoRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.rest.ClienteCep;
import petfinder.petfinderapi.rest.DistanciaResposta;
import petfinder.petfinderapi.utilitarios.FilaObj;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/instituicoes")
@Tag(name = "Instituições e Endereço", description = "Essa API é reponsável por fazer requisições da instituição e do endereço")
public class InsitituicoesController {

    // repositorios
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    @Autowired
    private ClienteCep clienteCep;

    private FilaObj filaObj = new FilaObj<>(10);

    // endpoints

    // retorna todas as instituições
    @GetMapping
    @Operation(description = "Endpoint que retorna uma lista de todas as suas instituições")
    public ResponseEntity<List<Instituicao>> listarInstituicoes(){

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
    @Operation(description = "Endpoint para cadastro de instituição")
    public ResponseEntity<Object> postInstituicao(@RequestBody @Valid Instituicao instituicao){
            // adicionando endereco + instituicao
            // enderecoRepositorio.save(instituicao.getEndereco());
            instituicaoRepositorio.save(instituicao);

            // 201
            return ResponseEntity.status(201).build();
    }

    // edita dados da instituicao
    @PutMapping("/{id}")
    @Operation(description = "Endpoint para edição das informações da instituição")
    public ResponseEntity<Object> putInstituicao(@RequestBody @Valid Instituicao instituicaoAtualizada, @PathVariable int id){

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
            enderecoRepositorio.save(endereco);

            // 201
            return ResponseEntity.status(201).build();
    }

    // edita um endereco especifico
    @PutMapping("/endereco/{id}")
    @Operation(description = "Endpoint para edição de um endereço filtrado pelo ID")
    public ResponseEntity<Object> putEndereco(@RequestBody @Valid Endereco endereco, @PathVariable int id) {

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

        DistanciaResposta clienteDistancia = clienteCep.getDistancia(cepUsuario, cepInstituicao);

        if (clienteDistancia != null) {
            return ResponseEntity.status(200).body(clienteDistancia.getDistancia());
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/distancias/{cepUsuario}/{distanciaMax}")
    public ResponseEntity getListaDistanciasInstituicaoes(@PathVariable String cepUsuario,
                                                     @PathVariable Integer distanciaMax) {
        List<Instituicao> lista = instituicaoRepositorio.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        for (int i = 0; i < lista.size(); i++) {
            DistanciaResposta resposta = clienteCep.getDistancia(cepUsuario, lista.get(i).getEndereco().getCep());
            Integer distancia = resposta.getDistancia();

            if (distancia <= distanciaMax) {
                filaObj.insert(lista.get(i));
            }
        }

        return ResponseEntity.status(200).body(filaObj.getFila());
    }
}
