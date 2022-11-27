package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Premio;
import petfinder.petfinderapi.resposta.PremioDto;
import petfinder.petfinderapi.resposta.PremioDtoData;
import java.util.List;

public interface PremioRepositorio extends JpaRepository<Premio, Integer> {

    List<Premio> findAll();

    List<Premio> findById(int id);

    @Query("SELECT p FROM Premio p WHERE p.pet.id = ?1")
    List<Premio> findByPetId(int id);

    void deleteById(int id);

    @Query("SELECT count(p) FROM Premio p WHERE p.pet.instituicao.id = ?1")
    public Integer findPremioPorPetInstituicao(int idInstuicao);

    @Query("SELECT count(distinct p.pet.id) FROM Premio p WHERE p.pet.instituicao.id = ?1")
    public Integer countPetSemPremioInstituicao(int idInstituicao);

    @Query("SELECT new petfinder.petfinderapi.resposta.PremioDto(p) FROM Premio p where p.pet.id = ?1")
    List<PremioDto> findByPetIdDto(int id);

    @Query("SELECT new petfinder.petfinderapi.resposta.PremioDto(p) from Premio p where p.pet.id IN (SELECT d.pet FROM Demanda d WHERE (d.status = 'CONCLUIDO' OR d.status = 'PGTO_REALIZADO_INST') AND d.dataFechamento LIKE '2022-08-%' AND d.usuario.id = ?1)")
    List<PremioDto> findByUsuarioIdDto(int idUser);

    @Query("SELECT new petfinder.petfinderapi.resposta.PremioDtoData(p) from Premio p where p.pet.instituicao.id = ?1")
    List<PremioDtoData> findByInstituicaoId(int idInst);
    @Query("SELECT new petfinder.petfinderapi.resposta.PremioDtoData(p) from Premio p where p.pet.instituicao.id = ?1 AND dataEnvio like CONCAT(?2,'-',?3,'%')")
    List<PremioDtoData> findByInstituicaoIdMes(int idInst, String ano, String mes);

    @Query("SELECT new petfinder.petfinderapi.resposta.PremioDtoData(p) from Premio p where p.pet.instituicao.id = ?1 AND dataEnvio like CONCAT(?2,'-',?3,'-',?4,'%')")
    List<PremioDtoData> findByInstituicaoIdSemana(int idInst, String ano, String mes, String dia);
}