package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.PetHasCaracteristica;

import java.util.List;

public interface PetHasCaracteristicaRepositorio extends JpaRepository<PetHasCaracteristica, Integer> {

    List<PetHasCaracteristica> findByFkPetId(int id);
/*
SELECT *
FROM CARACTERISTICA AS C
JOIN PET_HAS_CARACTERISTICA AS P ON P.FK_CARACTERISTICA_ID = C.ID
 */
    //@Query("select C from Caracteristica C\n" +
            //"JOIN PetHasCaracteristica P WHERE P.Fk.Caracteristica_Id = C.Id")
    //List<PetHasCaracteristica> findByFkCaracteristica(String caracteristica);

    void deleteByFkPet(Integer fkPet);

    boolean existsByFkPet(Integer fkPet);

    @Query("SELECT u FROM PetHasCaracteristica u WHERE u.fkCaracteristica.id = ?1")
    List<PetHasCaracteristica> findByFkCaracteriticaId(int id);
}
