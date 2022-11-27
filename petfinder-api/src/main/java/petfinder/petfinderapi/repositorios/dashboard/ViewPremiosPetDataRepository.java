package petfinder.petfinderapi.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewPremiosPetData;

public interface ViewPremiosPetDataRepository extends JpaRepository<ViewPremiosPetData, String> {
    
    @Query("SELECT AVG(v.premios) FROM ViewPremiosPetData v WHERE v.petId = ?1")
    public Double findMediaPremiosByPetId(Integer petId);
    
}
