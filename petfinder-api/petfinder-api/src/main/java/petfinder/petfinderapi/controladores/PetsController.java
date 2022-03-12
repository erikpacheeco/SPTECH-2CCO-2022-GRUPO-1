package petfinder.petfinderapi.controladores;

import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Pet;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {

    public static List<Pet> pets = new ArrayList<>();

    @GetMapping
    public List<Pet> listarPets(){
        return pets;
    }

}
