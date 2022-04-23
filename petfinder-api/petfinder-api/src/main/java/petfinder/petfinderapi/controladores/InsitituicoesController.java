package petfinder.petfinderapi.controladores;

import org.apache.catalina.connector.Response;
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

    // List<Instituicao> instituicoes = new ArrayList<>();

    // endereco
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    // =================================================================================
    // CONTROLE INSTITUIÇÃO
    // =================================================================================

    // retorna todas as instituições
    @GetMapping
    public ResponseEntity<List<Instituicao>> listarInstituicoes(){

        List<Instituicao> instituicoes = instituicaoRepositorio.findAll();

        if(instituicoes.isEmpty()) {
            return ResponseEntity.status(204).body(instituicoes);
        }

        return ResponseEntity.status(200).body(instituicoes);
    }

    // retorna instituições + seus endereços
    @GetMapping("/completa")
    public ResponseEntity<List<InstituicaoEndereco>> getInstituicaoEndereco() {

        List<Instituicao> instituicoes = instituicaoRepositorio.findAll();
        List<InstituicaoEndereco> instituicaoEnderecos = new ArrayList<InstituicaoEndereco>();

        instituicoes.stream().forEach(instituicao -> {
            Endereco endereco = enderecoRepositorio.findById(instituicao.getFkEndereco()).get();
            InstituicaoEndereco instituicaoEndereco = new InstituicaoEndereco(instituicao, endereco);
            instituicaoEnderecos.add(instituicaoEndereco);
        });

        if (instituicaoEnderecos.isEmpty()) {
            return ResponseEntity.status(200).body(instituicaoEnderecos);
        }
        
        return ResponseEntity.status(200).body(instituicaoEnderecos);
    }

    // retorna instituicao baseada no ID
    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> getInstituicaoById(@PathVariable int id) {

        Optional<Instituicao> instituicao = instituicaoRepositorio.findById(id);

        if(instituicao.isPresent()) {
            return ResponseEntity.status(200).body(instituicao.get());
        }

        return ResponseEntity.status(400).build();
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

            Integer fkEndereco = instituicao.get().getFkEndereco();

            // verificando existencia do endereço da instituição
            if (Objects.nonNull(fkEndereco)) {
                endereco = enderecoRepositorio.findById(instituicao.get().getFkEndereco());
            } 

            instituicaoEndereco = new InstituicaoEndereco(
                instituicao.get(), 
                Objects.nonNull(endereco) ? endereco.get() : null
            );

            // response
            return ResponseEntity.status(200).body(instituicaoEndereco);
        }
        
        // response
        return ResponseEntity.status(404).build();
    }

    // cadastra instituicao
    @PostMapping
    public ResponseEntity<Object> postInstituicao(@RequestBody @Valid Instituicao instituicao){

        if(Objects.nonNull(instituicao)) {

            // adicionando endereco + instituicao
            // enderecoRepositorio.save(instituicao.getEndereco());
            instituicaoRepositorio.save(instituicao);

            return ResponseEntity.status(201).build();
        }
        
        return ResponseEntity.status(400).build();
    }

    // edita dados da instituicao
    @PutMapping("/{indice}")
    public ResponseEntity<Object> putInstituicao(@RequestBody @Valid Instituicao instituicaoAtualizada, @PathVariable int indice){
        if(instituicaoRepositorio.existsById(indice)){
            instituicaoAtualizada.setId(indice);
            instituicaoRepositorio.save(instituicaoAtualizada);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    // =================================================================================
    // CONTROLE ENDEREÇO
    // =================================================================================

    // retorna todos os enderecos
    @GetMapping("/endereco")
    public ResponseEntity<List<Endereco>> getAllEndereco() {

        List<Endereco> enderecos = enderecoRepositorio.findAll();

        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).body(enderecos);
        } 

        return ResponseEntity.status(200).body(enderecos);
    }

    // retorna endereco específico
    @GetMapping("/endereco/{id}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable int id) {
        Optional<Endereco> endereco = enderecoRepositorio.findById(id);

        if(endereco.isPresent()) {
            return ResponseEntity.status(200).body(endereco.get());
        }

        return ResponseEntity.status(404).build();
    }

    // cadastra endereco
    @PostMapping("/endereco")
    public ResponseEntity<Object> postEndereco(@RequestBody @Valid Endereco endereco) {
        if (Objects.nonNull(endereco)) {
            enderecoRepositorio.save(endereco);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    // edita um endereco especifico
    @PutMapping("/endereco/{id}")
    public ResponseEntity<Object> putEndereco(@RequestBody @Valid Endereco endereco, @PathVariable int id) {
        if (enderecoRepositorio.findById(id).isPresent()) {
            endereco.setId(id);
            enderecoRepositorio.save(endereco);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(400).build();
    }

    // ======================================================================
    // OLD CODE
    // ======================================================================

    // @PostMapping("/colab/{indiceInst}/{indiceColab}")
    // public String assocColaborador(@PathVariable int indiceInst,@PathVariable int indiceColab){
    //     if(!(instituicoes.size() <= indiceInst)){
    //         if(!(ColaboradoresController.colaboradores.size() <= indiceColab)){
    //             ColaboradoresController.colaboradores.get(indiceColab).setInstituicao(instituicoes.get(indiceInst));
    //             return "Colaborador associado com sucesso";
    //         }
    //         return "Código de colaborador não existente";
    //     }
    //     return "Código de instituição não existente";
    // }

    // @PostMapping("/pet/{indiceInst}/{indicePet}")
    // public String assocPet(@PathVariable int indiceInst,@PathVariable int indicePet){
    //     if(!(instituicoes.size() <= indiceInst)) {
    //         if (!(PetsController.pets.size() <= indicePet)) {
    //             PetsController.pets.get(indicePet).setInstituicao(instituicoes.get(indiceInst));
    //             return "Pet associado com sucesso";
    //         }
    //         return "Código de Pet não existente";
    //     }
    //     return "Código de instituição não existente";
    // }
}
