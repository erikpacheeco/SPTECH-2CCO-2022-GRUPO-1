package petfinder.petfinderapi.controladores;

import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Instituicao;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instituicoes")
public class InsitituicoesController {

    List<Instituicao> instituicoes = new ArrayList<>();

    @GetMapping
    public List<Instituicao> listarInstituicoes(){
        return instituicoes;
    }

    @PostMapping
    public String addInstituicao(@RequestBody Instituicao instituicao){
        if(instituicao.validar()){
            instituicoes.add(instituicao);
            return "Nova Instituição cadastrada com sucesso!";
        }
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
