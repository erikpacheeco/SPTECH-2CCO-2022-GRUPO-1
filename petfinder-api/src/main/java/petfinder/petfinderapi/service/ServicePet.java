package petfinder.petfinderapi.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.resposta.PetPerfil;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;

@Service
public class ServicePet {
    
    @Autowired
    private PetRepositorio repository;

    // success:     return pet profile data
    // fail:        throw IdNotFoundException
    public PetPerfil getPetPerfil(int id) {

        // querying pet on database
        Optional<PetPerfil> optional = repository.findPetPerfilById(id);

        // 200 ok
        if (optional.isPresent()) {
            return optional.get();
        }

        // 404 not found
        throw new EntityNotFoundException(id);
    }
}
