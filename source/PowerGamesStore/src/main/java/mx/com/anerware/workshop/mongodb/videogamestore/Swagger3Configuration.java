package mx.com.anerware.workshop.mongodb.videogamestore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class Swagger3Configuration {
	@Bean
	public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Game Store API").description(
                        "This is a Game Store API RESTful service.").version("1.0.0.1"));
    }
}
