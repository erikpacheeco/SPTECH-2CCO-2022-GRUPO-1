package petfinder.petfinderapi.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.service.exceptions.IdNotFoundException;

@Service
public class ServicePet {
    
    @Autowired
    private PetRepositorio repository;

    // retorna dados para o perfil do pet
    public Pet getPetPerfil(int id) {

        Optional<Pet> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        throw new IdNotFoundException(id, "pet id");
    }
}
