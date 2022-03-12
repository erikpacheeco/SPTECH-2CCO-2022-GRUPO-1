package petfinder.petfinderapi.controladores;

import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Pet;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @PostMapping
    public String addPet(@RequestBody Pet novoPet){
        PetsController.pets.add(novoPet);
        return "Pet cadastrado com sucesso!";
    }

    @PostMapping("/finalizar-adc")
    public String finalizarAdocao(@RequestBody int indice){
//      VALIDAR EXISTENCIA DO PET E MUDAR ATRIBUTO DO MESMO
        for (Pet p : PetsController.pets) {
            if (p.getNome().equals(PetsController.pets.get(indice).getNome())
                    && p.getRaca().equals(PetsController.pets.get(indice).getRaca())
                    && p.getTipo().equals(PetsController.pets.get(indice).getTipo())
                    && p.getPorte().equals(PetsController.pets.get(indice).getPorte())
                    && p.getTelefone().equals(PetsController.pets.get(indice).getTelefone())
            ) {
                p.setAdotado(true);
            }
        }

        if (PetsController.pets.get(indice).isAdotado().equals(true)) {
            PetsController.pets.remove(indice);
            return "O status do pet foi atualizado e removido da lista de adoção!";
        } else {
            return "O pet ainda está disponível para adoção";
        }

    }

    @PutMapping("/atualizar-pet/{indice}")
    public String editarPet(
            @PathVariable int indice,
            @RequestBody Pet petAtualizado)
    {
        PetsController.pets.set(indice, petAtualizado);
        return "O pet foi atualizado com sucesso!";
    }


}
