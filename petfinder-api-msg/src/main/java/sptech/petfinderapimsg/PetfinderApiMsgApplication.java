package sptech.petfinderapimsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class PetfinderApiMsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetfinderApiMsgApplication.class, args);
	}
	@Bean
	public OpenAPI petfinderAPI(){
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("Petfinder API")
						.description("Documentação da API de mensagens da empresa Petfinder")
						.version("1.0.0"));
	}

}
