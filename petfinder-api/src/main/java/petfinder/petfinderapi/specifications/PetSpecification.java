package petfinder.petfinderapi.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.resposta.PetPerfil;

public class PetSpecification {
    public static Specification<PetPerfil> porteIn(List<String> porte){
        return (root, query, builder) -> builder.in(root.get("porte").in(porte));
    }
}