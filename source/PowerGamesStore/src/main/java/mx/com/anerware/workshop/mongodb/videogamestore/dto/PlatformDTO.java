package mx.com.anerware.workshop.mongodb.videogamestore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PlatformDTO {
	@Schema(description = "Name of the Platform", 
            example = "Sega", required = true)
    @NotBlank
    @Size(max = 100)
	public String name;
	
	@Schema(description = "Name of the video game console", 
            example = "Sega Genesis", required = true)
    @NotBlank
    @Size(max = 100)
	public String system;
	
	@Schema(description = "Name of the country of the Platform", 
            example = "Japan", required = true)
    @NotBlank
    @Size(max = 100)
	public String country;
}