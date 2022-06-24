package mx.com.anerware.workshop.mongodb.videogamestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PowerGamesStoreApplication 
             extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(PowerGamesStoreApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder 
	   application) {
	      return application.sources(PowerGamesStoreApplication.class);
	}
}
