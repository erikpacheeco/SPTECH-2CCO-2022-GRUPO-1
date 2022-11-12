package petfinder.petfinderapi.controladores.util;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class HeaderConfig {
    
    public static URI getLocation(Integer id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    public static URI getCurentRequest() {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand().toUri();
    }
    
}
