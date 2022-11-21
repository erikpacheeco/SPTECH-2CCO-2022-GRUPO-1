package petfinder.petfinderapi.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import petfinder.petfinderapi.entidades.Pet;

public class PetSpecification {

    public static Specification<Pet> porteIn(List<String> porte){
        return (root, query, builder) -> builder.in(root.get("porte")).value(porte);
    }

    public static Specification<Pet> especieIn(List<String> especie){
        return (root, query, builder) -> builder.in(root.get("especie")).value(especie);
    }

    public static Specification<Pet> sexoIn(List<String> sexo){
        return (root, query, builder) -> builder.in(root.get("sexo")).value(sexo);
    }

    public static Specification<Pet> isDoenteIn(List<Boolean> isDoente){
        return (root, query, builder) -> builder.in(root.get("doente")).value(isDoente);
    }

    public static Specification<Pet> adotado(){
        return (root, query, builder) -> builder.in(root.get("adotado")).value(false);
    }
}