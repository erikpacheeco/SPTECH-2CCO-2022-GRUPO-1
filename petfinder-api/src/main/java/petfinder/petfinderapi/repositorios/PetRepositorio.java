package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.resposta.PetPerfil;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PetRepositorio extends JpaRepository<Pet, Integer>, JpaSpecificationExecutor<PetPerfil> {

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

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p WHERE p.id IN (SELECT DISTINCT d.pet.id FROM Demanda d WHERE d.categoria LIKE 'PAGAMENTO' AND d.pet.id IS NOT NULL AND d.status LIKE 'CONCLUIDO' AND d.usuario.id = ?1 AND d.dataFechamento >= ?2)")
    public List<PetPerfil> findPetByDemandaApadrinhamentoAndUsuario(int idUser, Date date);

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p WHERE p.adotado = false AND p.id IN (SELECT h.pet.id FROM PetHasCaracteristica h WHERE h.caracteristica.id IN (SELECT c.id FROM Caracteristica c WHERE c.id IN (SELECT u.caracteristica.id FROM UsuarioHasInteresse u WHERE u.usuario.id = ?1)))")
    public List<PetPerfil> findByUserPreferences(int idUser);

    @Query("SELECT new petfinder.petfinderapi.resposta.PetPerfil(p) FROM Pet p WHERE p.adotado = false AND p.id NOT IN (SELECT h.pet.id FROM PetHasCaracteristica h WHERE h.caracteristica.id IN (SELECT c.id FROM Caracteristica c WHERE c.id IN (SELECT u.caracteristica.id FROM UsuarioHasInteresse u WHERE u.usuario.id = ?1)))")
    public List<PetPerfil> findNotByUserPreferences(int idUser);
}