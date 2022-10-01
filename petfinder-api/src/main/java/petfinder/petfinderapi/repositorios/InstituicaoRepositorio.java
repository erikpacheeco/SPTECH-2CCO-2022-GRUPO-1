package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Instituicao;

public interface InstituicaoRepositorio extends JpaRepository<Instituicao, Integer>{
    
    public Boolean existsById(int id);

    @Query("SELECT count(i) FROM Instituicao i")
    public Integer findQtdInstituicao();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.instituicao.id = ?1")
    public Integer findAllColaboradoresInstituicao(int idInstituicao);
    
}
