package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.repositorios.EnderecoRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
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
    private EnderecoRepositorio enderecoRepository;

    @Autowired
    private InstituicaoRepositorio instituicaoRepository;

    // =================================================================================
    // CONTROLE INSTITUIÇÃO
    // =================================================================================

    // retorna todas as instituições
    @GetMapping
    public ResponseEntity<List<Instituicao>> listarInstituicoes(){

        List<Instituicao> instituicoes = instituicaoRepository.findAll();

        if(instituicoes.isEmpty()) {
            return ResponseEntity.status(204).body(instituicoes);
        }

        return ResponseEntity.status(200).body(instituicoes);
    }

    // retorna instituicao baseada no ID
    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> getInstituicaoById(@PathVariable int id) {

        Optional<Instituicao> instituicao = instituicaoRepository.findById(id);

        if(instituicao.isPresent()) {
            return ResponseEntity.status(200).body(instituicao.get());
        }

        return ResponseEntity.status(400).build();
    }

    // cadastra instituicao
    @PostMapping
    public ResponseEntity<Object> addInstituicao(@RequestBody @Valid Instituicao instituicao){

        if(Objects.nonNull(instituicao) && Objects.nonNull(instituicao.getEndereco())) {

            // adicionando endereco + instituicao
            enderecoRepository.save(instituicao.getEndereco());
            instituicaoRepository.save(instituicao);

            return ResponseEntity.status(201).build();
        }
        
        return ResponseEntity.status(400).build();
    }

    // edita dados da instituicao
    @PutMapping("/{indice}")
    public ResponseEntity<Object> putInstituicao(@RequestBody @Valid Instituicao instituicaoAtualizada, @PathVariable int indice){
        if(instituicaoRepository.existsById(indice)){
            instituicaoAtualizada.setId(indice);
            instituicaoRepository.save(instituicaoAtualizada);
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

        List<Endereco> enderecos = enderecoRepository.findAll();

        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).body(enderecos);
        } 

        return ResponseEntity.status(200).body(enderecos);
    }

    // retorna endereco específico
    @GetMapping("/endereco/{id}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable int id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);

        if(endereco.isPresent()) {
            return ResponseEntity.status(200).body(endereco.get());
        }

        return ResponseEntity.status(404).build();
    }

    // cadastra endereco
    @PostMapping("/endereco")
    public ResponseEntity<Object> postEndereco(@RequestBody @Valid Endereco endereco) {
        if (Objects.nonNull(endereco)) {
            enderecoRepository.save(endereco);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    // edita um endereco especifico
    @PutMapping("/endereco/{id}")
    public ResponseEntity<Object> putEndereco(@RequestBody @Valid Endereco endereco, @PathVariable int id) {
        if (enderecoRepository.findById(id).isPresent()) {
            endereco.setId(id);
            enderecoRepository.save(endereco);
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
