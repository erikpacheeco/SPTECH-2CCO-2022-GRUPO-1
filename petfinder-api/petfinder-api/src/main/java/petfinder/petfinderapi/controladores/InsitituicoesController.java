package petfinder.petfinderapi.controladores;

import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Instituicao;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instituicoes")
public class InsitituicoesController {

    List<Instituicao> instituicoes = new ArrayList<>();

    @PostMapping
    public String addInstituicao(@RequestBody Instituicao instituicao){
        instituicoes.add(instituicao);
        return "Nova Instituição cadastrada com sucesso!";
    }

    @PostMapping("/{indiceInst}/{indiceColab}")
    public String assocColaborador(@PathVariable int indiceInst,@PathVariable int indiceColab){
        if(!(ColaboradoresController.colaboradores.size() <= indiceColab)){
            ColaboradoresController.colaboradores.get(indiceColab).setInstituicao(instituicoes.get(indiceInst));
            return "Colaborador associado com sucesso";
        }
        return "Código de colaborador não existente";
    }

    @PostMapping("/{indiceInst}/{indicePet}")
    public String assocPet(@PathVariable int indiceInst,@PathVariable int indicePet){
        if(!(PetsController.pets.size() <= indicePet)){
            PetsController.pets.get(indicePet).setInstituicao(instituicoes.get(indiceInst));
            return "Pet associado com sucesso";
        }
        return "Código de Pet não existente";
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
