package mx.com.anerware.workshop.mongodb.videogamestore.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Anerware
 * 
 */
@Data
public class GameStoreDTO {
	@Schema(description = "Unique identifier of the Video Game.", 
            example = "1", required = true)
	public Long videoGameIdx;
	
	@Schema(description = "Name of the Video Game.", 
            example = "Super Mario Bros", required = true)
    @NotBlank
    @Size(max = 100)
	public String title;
	
	@Schema(description = "synopsis of the Video Game.", 
            example = "The masterpiece of shigeru miyamoto, the new adventure of the plumber", required = true)
    @NotBlank
    @Size(max = 500)
	public String description;
	
	@Schema(description = "Name of the development company", 
            example = "Konami", required = true)
    @NotBlank
    @Size(max = 100)
	public String developedBy;
	
	@Schema(description = "release year of the game", 
            example = "1993", required = true)
    @NotBlank
    @Min(value = 1970, message = "year should not be less than 1970")
    @Max(value = 2100, message = "year should not be greater than 2100")
	public Integer year;
	public PlatformDTO platform;
	
	@Schema(description = "release year of the game", 
            example = "1", required = true)
    @NotBlank
    @Min(value = 1, message = "year should not be less than 1")
    @Max(value = 100, message = "year should not be greater than 100")
	public Integer players;
}