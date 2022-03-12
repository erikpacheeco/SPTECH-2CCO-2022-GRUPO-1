package petfinder.petfinderapi.controladores;

import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Colaborador;
import petfinder.petfinderapi.entidades.Pet;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradoresController {

    public static List<Colaborador> colaboradores = new ArrayList<>();

    @PostMapping("/{indiceColab}")
    public String addPet(@RequestBody Pet novoPet, @PathVariable int indiceColab){
        novoPet.setInstituicao(colaboradores.get(indiceColab).getInstituicao());
        PetsController.pets.add(novoPet);
        return "Pet cadastrado com sucesso!";
    }

    @DeleteMapping("/{indicePet}/{indiceColab}")
    public String finalizarAdocao(@PathVariable int indicePet,@PathVariable int indiceColab){
        if(!(PetsController.pets.size() <= indicePet)){
            if(!(colaboradores.size() <= indicePet)){
                if(PetsController.pets.get(indicePet).getInstituicao().equals(colaboradores.get(indiceColab).getInstituicao())){
                    if (PetsController.pets.get(indicePet).isEmAdocao().equals(true)) {
                        PetsController.pets.remove(indicePet);
                        return "O status do pet foi atualizado e removido da lista de adoção!";
                    } else {
                        return "O pet ainda está disponível para adoção";
                    }
                }
                return "O colaborador não é da mesma instituição que o pet";
            }
            return "Código do Colaborador não encontrado!";
        }
        return "Código do Pet não encontrado!";
    }

    @PutMapping("/{indice}")
    public String editarPet(
            @PathVariable int indice,
            @RequestBody Pet petAtualizado)
    {
        if(!(PetsController.pets.size() <= indice)){
            PetsController.pets.set(indice, petAtualizado);
            return "O pet foi atualizado com sucesso!";
        }
        return "Código do Pet não encontrado!";
    }


}
