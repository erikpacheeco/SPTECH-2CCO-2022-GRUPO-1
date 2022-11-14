package petfinder.petfinderapi.controladores;

import static org.springframework.http.ResponseEntity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
@Tag(name = "Dashboard",description = "Essa API é utilizada para controlar as transações do sistema")
public class DashboardController {
    
    // Services

    // Endpoints
    @GetMapping("/test")
    @Operation(description = "")
    public ResponseEntity<String> getTest(){
        return ok("success");
    }

}
