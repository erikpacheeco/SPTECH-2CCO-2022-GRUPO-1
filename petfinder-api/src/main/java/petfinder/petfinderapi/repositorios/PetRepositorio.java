package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.resposta.PetPerfil;

import java.util.List;
import java.util.Optional;

public interface PetRepositorio extends JpaRepository<Pet, Integer> {

    List<Pet> findAll();
  
    @Query("SELECT u FROM Pet u WHERE u.doente = 'true' and u.adotado = 'false'")
    List<Pet> findByDoenteAndAdotado();
  
    List<Pet> findByFkInstituicaoId(int id);

    void deleteById(int id);

    @Query("SELECT u FROM Pet u WHERE u.especie = ?1")
    List<Pet> findByEspecieIgnoreCase(String especie);

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p WHERE p.id = ?1")
    public Optional<PetPerfil> findPetPerfilById(int id);

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p")
    public List<PetPerfil> findAllPetPerfil();

}
