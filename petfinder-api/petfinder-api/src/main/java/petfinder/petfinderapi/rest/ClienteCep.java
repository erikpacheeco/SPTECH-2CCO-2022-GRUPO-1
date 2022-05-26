package petfinder.petfinderapi.rest.apiCep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cep", url = "https://distancep.herokuapp.com/distance/")
public interface ClienteCep {

    @GetMapping("/{cepUsuario}/{cepInstituicao}")
    DistanciaResposta getDistancia(@PathVariable String cepUsuario,
                                   @PathVariable String cepInstituicao);
}
