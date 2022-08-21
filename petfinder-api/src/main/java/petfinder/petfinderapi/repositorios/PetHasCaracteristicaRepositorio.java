package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.PetHasCaracteristica;

import java.util.List;

public interface PetHasCaracteristicaRepositorio extends JpaRepository<PetHasCaracteristica, Integer> {

    // @Query("")
    @Query("SELECT c from PetHasCaracteristica c WHERE c.id = ?1")
    List<PetHasCaracteristica> findByPetId(int id);
    /*
    SELECT *
    FROM CARACTERISTICA AS C
    JOIN PET_HAS_CARACTERISTICA AS P ON P.FK_CARACTERISTICA_ID = C.ID
    */
    //@Query("select C from Caracteristica C\n" +
            //"JOIN PetHasCaracteristica P WHERE P.Fk.Caracteristica_Id = C.Id")
    //List<PetHasCaracteristica> findByFkCaracteristica(String caracteristica);

    // void deleteByPetId(Integer fkPet);

    // boolean existsByPetId(Integer fkPet);

    @Query("SELECT u FROM PetHasCaracteristica u WHERE u.caracteristica.id = ?1")
    public List<PetHasCaracteristica> findByFkCaracteriticaId(int id);
}
