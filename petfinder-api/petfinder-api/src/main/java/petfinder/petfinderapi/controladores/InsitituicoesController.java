package petfinder.petfinderapi.controladores;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.repositorios.EnderecoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/instituicoes")
public class InsitituicoesController {

    List<Instituicao> instituicoes = new ArrayList<>();

    // endereco
    @Autowired
    private EnderecoRepositorio endRepository;

    // retorna todos os enderecos
    @GetMapping("/endereco")
    public ResponseEntity<List<Endereco>> getAllEndereco() {

        List<Endereco> enderecos = endRepository.findAll();

        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).body(enderecos);
        } 

        return ResponseEntity.status(200).body(enderecos);
    }

    // retorna endereco específico
    @GetMapping("/endereco/{id}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable int id) {
        Optional<Endereco> endereco = endRepository.findById(id);

        if(endereco.isPresent()) {
            return ResponseEntity.status(200).body(endereco.get());
        }

        return ResponseEntity.status(404).build();
    }

    // cadastra endereco
    @PostMapping("/endereco")
    public ResponseEntity<Object> postEndereco(@RequestBody Endereco endereco) {
        if (Objects.nonNull(endereco)) {
            endRepository.save(endereco);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/endereco/{id}")
    public ResponseEntity<Object> putEndereco(@RequestBody Endereco endereco, @PathVariable int id) {
        if (endRepository.findById(id).isPresent()) {
            endereco.setId(id);
            endRepository.save(endereco);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(400).build();
    }

    // ========================================================================================
    // old code
    // ========================================================================================
    @GetMapping
    public List<Instituicao> listarInstituicoes(){
        return instituicoes;
    }

    @PostMapping
    public String addInstituicao(@RequestBody Instituicao instituicao){
        // if(instituicao.validar()){
        //     instituicoes.add(instituicao);
        //     return "Nova Instituição cadastrada com sucesso!";
        // }
        return "Dados Incorretos, por favor verifique e tente novamente";
    }

    @PostMapping("/colab/{indiceInst}/{indiceColab}")
    public String assocColaborador(@PathVariable int indiceInst,@PathVariable int indiceColab){
        if(!(instituicoes.size() <= indiceInst)){
            if(!(ColaboradoresController.colaboradores.size() <= indiceColab)){
                ColaboradoresController.colaboradores.get(indiceColab).setInstituicao(instituicoes.get(indiceInst));
                return "Colaborador associado com sucesso";
            }
            return "Código de colaborador não existente";
        }
        return "Código de instituição não existente";
    }

    @PostMapping("/pet/{indiceInst}/{indicePet}")
    public String assocPet(@PathVariable int indiceInst,@PathVariable int indicePet){
        if(!(instituicoes.size() <= indiceInst)) {
            if (!(PetsController.pets.size() <= indicePet)) {
                PetsController.pets.get(indicePet).setInstituicao(instituicoes.get(indiceInst));
                return "Pet associado com sucesso";
            }
            return "Código de Pet não existente";
        }
        return "Código de instituição não existente";
    }

    @PutMapping("/{indice}")
    public String editarInstituicao(@RequestBody Instituicao instituicaoAtualizada, @PathVariable int indice){
        if(!(instituicoes.size() <= indice)){
            instituicoes.set(indice,instituicaoAtualizada);
            return "Instituição atualizada com sucesso!";
        }
        return "Código de Instituição não existente";
    }

}
