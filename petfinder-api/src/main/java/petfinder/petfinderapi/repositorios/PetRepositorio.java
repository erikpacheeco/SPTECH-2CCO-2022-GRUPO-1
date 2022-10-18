package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.resposta.PetPerfil;
import java.util.List;
import java.util.Optional;

public interface PetRepositorio extends JpaRepository<Pet, Integer> {

    List<Pet> findAll();
  
    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p WHERE p.doente = 'true' and p.adotado = 'false'")
    List<PetPerfil> findByDoenteAndAdotado();

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p WHERE  p.adotado = 'false'")
    List<PetPerfil> findByAdotado();
  
    List<Pet> findByInstituicaoId(int id);

    void deleteById(int id);

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(u) FROM Pet u WHERE u.especie = ?1")
    List<PetPerfil> findByEspecieIgnoreCase(String especie);

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p WHERE p.id = ?1")
    public Optional<PetPerfil> findPetPerfilById(int id);

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p")
    public List<PetPerfil> findAllPetPerfil();

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p WHERE p.instituicao.id = ?1")
    List<PetPerfil> findPetPerfilByInstituicao(int id);

    @Query("SELECT count(p) FROM Pet p WHERE p.adotado <> 'true'")
    public Integer findAllPet();

    @Query("SELECT count(p) FROM Demanda p WHERE p.categoria = 'ADOCAO' AND p.instituicao.id = ?1 AND p.status = 'CONCLUIDO'")
    public Integer findAllAdotadoInstituicao(int idInstituicao);

    @Query("SELECT COUNT(p) FROM Pet p WHERE p.instituicao.id = ?1")
    public Integer findAllPetInstituicao(int idInstituicao);

    @Query("SELECT DISTINCT p.especie FROM Pet p")
    public List<String> findDistinctByEspecie();

    @Query("SELECT DISTINCT new petfinder.petfinderapi.resposta.PetPerfil(d.pet) FROM Demanda d WHERE d.status = 'CONCLUIDO' AND d.dataFechamento LIKE '2022-08-%' AND d.usuario.id = ?1")
    public List<PetPerfil> findPetByDemandaApadrinhamentoAndUsuario(int idUser);


}