package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.repositorios.EnderecoRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.resposta.InstituicaoEndereco;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/instituicoes")
public class InsitituicoesController {

    // repositorios
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    // endpoints

    // retorna todas as instituições
    @GetMapping
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

    // retorna instituições + seus endereços
    @GetMapping("/completa")
    public ResponseEntity<List<InstituicaoEndereco>> getInstituicaoEndereco() {

        // entidades
        List<Instituicao> instituicoes = instituicaoRepositorio.findAll();
        List<InstituicaoEndereco> instituicaoEnderecos = new ArrayList<InstituicaoEndereco>();

        // atribuindo endereco de cada instituicao
        instituicoes.stream().forEach(instituicao -> {
            Endereco endereco = null;

            if (Objects.nonNull(instituicao.getFkEndereco())) {
                endereco = enderecoRepositorio.findById(instituicao.getFkEndereco()).get();
            } 

            InstituicaoEndereco instituicaoEndereco = new InstituicaoEndereco(instituicao, endereco);
            instituicaoEnderecos.add(instituicaoEndereco);
        });

        // 204 no content
        if (instituicaoEnderecos.isEmpty()) {
            return ResponseEntity.status(204).body(instituicaoEnderecos);
        }
        
        // 200
        return ResponseEntity.status(200).body(instituicaoEnderecos);
    }

    // retorna instituicao baseada no ID
    @GetMapping("/{id}")
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

    // retorna instituicao específica + seu Endereco
    @GetMapping("/{id}/completa")
    public ResponseEntity<InstituicaoEndereco> getInstituicaoEnderecoById(@PathVariable int id) {

        // dados do banco
        Optional<Instituicao> instituicao = instituicaoRepositorio.findById(id);
        Optional<Endereco> endereco = null;
        InstituicaoEndereco instituicaoEndereco;

        // verificando existencia da instituição
        if (instituicao.isPresent()) {

            // verificando existencia do endereço da instituição
            endereco = enderecoRepositorio.findById(instituicao.get().getFkEndereco());

            instituicaoEndereco = new InstituicaoEndereco(
                instituicao.get(), 
                endereco.get()
            );

            // 200
            return ResponseEntity.status(200).body(instituicaoEndereco);
        }
        
        // 404 instituicao não encontrada
        return ResponseEntity.status(404).build();
    }

    // cadastra instituicao
    @PostMapping
    public ResponseEntity<Object> postInstituicao(@RequestBody @Valid Instituicao instituicao){
            // adicionando endereco + instituicao
            // enderecoRepositorio.save(instituicao.getEndereco());
            instituicaoRepositorio.save(instituicao);

            // 201
            return ResponseEntity.status(201).build();
    }

    // edita dados da instituicao
    @PutMapping("/{indice}")
    public ResponseEntity<Object> putInstituicao(@RequestBody @Valid Instituicao instituicaoAtualizada, @PathVariable int indice){

        // verificando se instituicao existe
        if(instituicaoRepositorio.existsById(indice)){
            instituicaoAtualizada.setId(indice);
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
    public ResponseEntity<Object> postEndereco(@RequestBody @Valid Endereco endereco) {
            enderecoRepositorio.save(endereco);

            // 201
            return ResponseEntity.status(201).build();
    }

    // edita um endereco especifico
    @PutMapping("/endereco/{id}")
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
}
