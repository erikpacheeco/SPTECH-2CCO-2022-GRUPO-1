package petfinder.petfinderapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class PetfinderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetfinderApiApplication.class, args);
	}
	@Bean
	public OpenAPI petfinderAPI(){
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("Petfinder API")
						.description("Documentação da API geral da empresa Petfinder")
						.version("1.0.0"));
	}
}
