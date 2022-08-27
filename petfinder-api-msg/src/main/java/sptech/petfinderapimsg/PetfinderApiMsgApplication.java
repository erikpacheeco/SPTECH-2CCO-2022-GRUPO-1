package sptech.petfinderapimsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
						.description("Documentação da API geral da empresa Petfinder")
						.version("1.0.0"));
	}

}
