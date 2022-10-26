package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.resposta.PetPerfil;

import java.util.List;

public interface InstituicaoRepositorio extends JpaRepository<Instituicao, Integer>{
    
    public Boolean existsById(int id);

    @Query("SELECT count(i) FROM Instituicao i")
    public Integer findQtdInstituicao();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.instituicao.id = ?1")
    public Integer findAllColaboradoresInstituicao(int idInstituicao);

    @Query("SELECT DISTINCT d.instituicao FROM Demanda d WHERE d.status = 'CONCLUIDO' AND d.dataFechamento LIKE '2022-08-%' AND d.usuario.id = ?1")
    public List<Instituicao> findInstituicaoByDemandaApadrinhamentoAndUsuario(int idUser);
}
