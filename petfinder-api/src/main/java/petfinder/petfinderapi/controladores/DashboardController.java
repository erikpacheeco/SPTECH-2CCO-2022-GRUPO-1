package petfinder.petfinderapi.controladores;

import static org.springframework.http.ResponseEntity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import petfinder.petfinderapi.resposta.dashboard.DtoAdminResponse;
import petfinder.petfinderapi.resposta.dashboard.DtoChatopsResponse;
import petfinder.petfinderapi.resposta.dashboard.DtoSysadminResponse;
import petfinder.petfinderapi.service.ServiceRequest;
import petfinder.petfinderapi.service.dashboard.AdminService;
import petfinder.petfinderapi.service.dashboard.ChatopsService;
import petfinder.petfinderapi.service.dashboard.SysadminService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
@Tag(name = "Dashboard",description = "Essa API é utilizada para controlar as transações do sistema")
public class DashboardController {
    
    // Services
    @Autowired
    private AdminService adminService;

    @Autowired
    private ChatopsService chatopsService;

    @Autowired
    private SysadminService sysadminService;

    @Autowired
    private ServiceRequest serviceRequest;

    // Endpoints
    @GetMapping("/admin/{id}")
    @Operation(description = "")
    public ResponseEntity<DtoAdminResponse> getAdminDashboard(@PathVariable int id){
        serviceRequest.saveRequest();
        return ok(adminService.getAdminDashboard(id));
    }

    @GetMapping("/chatops/{id}")
    @Operation(description = "")
    public ResponseEntity<DtoChatopsResponse> getChatopsDashboard(@PathVariable int id){
        serviceRequest.saveRequest();
        return ok(chatopsService.getChatopsDashboard(id));
    }

    @GetMapping("/sysadmin/{id}")
    @Operation(description = "")
    public ResponseEntity<DtoSysadminResponse> getSysadminDashboard(@PathVariable int id){
        serviceRequest.saveRequest();
        return ok(sysadminService.getSysadminDashboard(id));
    }

}
