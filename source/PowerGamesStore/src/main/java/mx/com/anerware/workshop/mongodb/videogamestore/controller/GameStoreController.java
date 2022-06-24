package mx.com.anerware.workshop.mongodb.videogamestore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.anerware.workshop.mongodb.videogamestore.dto.GameStoreDTO;
import mx.com.anerware.workshop.mongodb.videogamestore.dto.PlatformDTO;
import mx.com.anerware.workshop.mongodb.videogamestore.entities.GameStore;
import mx.com.anerware.workshop.mongodb.videogamestore.service.GameStoreService;
import mx.com.anerware.workshop.mongodb.videogamestore.transformer.VideoGameTransformer;

@RestController
@RequestMapping("/gameStore")
@Tag(name = "Game Store", description = "The Game Store API")
public class GameStoreController {
	
	@Autowired
	private GameStoreService gameStoreService;
	
	@Operation(summary = "Find all video games", description = "Find all video games", tags = { "Video Games" })
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "successful operation",
	                content = @Content(schema = @Schema(implementation = GameStoreDTO.class))),
	        @ApiResponse(responseCode = "404", description = "Video Game not found") })
	@GetMapping(value="/getAll",  produces = { "application/json", "application/xml" })
	public ResponseEntity<?> getAllVideoGames(){
		List<GameStore> games = gameStoreService.findAll(); 
    	if(games != null && !games.isEmpty()) {
    	   return new ResponseEntity<List<GameStoreDTO>>(
    			      VideoGameTransformer.mapAll(games, 
    			                  GameStoreDTO.class), HttpStatus.OK);
    	}else{
    	   return new ResponseEntity<String>("Video Game not found", 
    			   HttpStatus.NOT_FOUND);
    	}
	}
	
    @Operation(summary = "Find Video Game by videoGameIdx", description = "Returns a single Video Game", tags = { "Video Games" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = GameStoreDTO.class))),
        @ApiResponse(responseCode = "404", description = "Video Game not found") })
	@GetMapping(value = "/byVideoGameIdx/{videoGameIdx}",  produces = { "application/json", "application/xml" })
    public ResponseEntity<?> getVideoGamesByVideoGameIdx(@PathVariable("videoGameIdx") Long videoGameIdx) {
    	GameStore game = gameStoreService.findByVideoGameIdx(videoGameIdx.longValue());
    	if(game != null) {
    	   return new ResponseEntity<GameStoreDTO>(
    			      VideoGameTransformer.map(game, GameStoreDTO.class), 
    			      HttpStatus.OK);
    	}else{
    	   return new ResponseEntity<String>("Video Game not found", 
    			   HttpStatus.NOT_FOUND);
    	}
    }

    @Operation(summary = "Find Video Games by year", description = "Returns a list of Video Games", tags = { "Video Games" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = GameStoreDTO.class))),
        @ApiResponse(responseCode = "404", description = "Video Game not found") })
    @GetMapping(value = "/byYear/{year}",  produces = { "application/json", "application/xml" })
    public ResponseEntity<?> getVideoGamesByYear(@PathVariable("year") Integer year) {
        List<GameStore> games = gameStoreService.findByYear(year.intValue()); 
    	if(games != null && !games.isEmpty()) {
    	   return new ResponseEntity<List<GameStoreDTO>>(
    			      VideoGameTransformer.mapAll(games, 
    			                  GameStoreDTO.class), HttpStatus.OK);
    	}else{
    	   return new ResponseEntity<String>("Video Game not found", 
    			   HttpStatus.NOT_FOUND);
    	}
    }

    @Operation(summary = "Find Video Games by developedBy", description = "Returns a list of Video Games", tags = { "Video Games" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = GameStoreDTO.class))),
        @ApiResponse(responseCode = "404", description = "Video Game not found") })
    @GetMapping(value = "/byDevelopedBy/{developedBy}", produces = { "application/json", "application/xml" })
    public ResponseEntity<?> getVideoGamesByDevelopedBy(@PathVariable("developedBy") String developedBy) {
    	List<GameStore> games = gameStoreService.findByDevelopedBy(validateInput(developedBy)); 
    	if(games != null && !games.isEmpty()) {
    	   return new ResponseEntity<List<GameStoreDTO>>(
    			      VideoGameTransformer.mapAll(games, 
    			                  GameStoreDTO.class), HttpStatus.OK);
    	}else{
    	   return new ResponseEntity<String>("Video Game not found", 
    			   HttpStatus.NOT_FOUND);
    	}
    }
    
    @Operation(summary = "Find Video Games by range of years", description = "Returns a list of Video Games", tags = { "Video Games" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = GameStoreDTO.class))),
        @ApiResponse(responseCode = "404", description = "Video Game not found") })
    @GetMapping(value = "/betweenYear",  produces = { "application/json", "application/xml" })
    public ResponseEntity<?> getVideoGamesBetweenYear(@RequestParam("initYear") int initYear, @RequestParam("endingYear") int endingYear){
    	List<GameStore> games = gameStoreService.findRecordBetweenYear(initYear,endingYear); 
    	if(games != null && !games.isEmpty()) {
    	   return new ResponseEntity<List<GameStoreDTO>>(
    			      VideoGameTransformer.mapAll(games, 
    			                  GameStoreDTO.class), HttpStatus.OK);
    	}else{
    	   return new ResponseEntity<String>("Video Game not found", 
    			   HttpStatus.NOT_FOUND);
    	}
    }

    @Operation(summary = "Add a new Video Game", description = "Adds a new Video Game to Store", tags = { "Video Games" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Video Game created",
                content = @Content(schema = @Schema(implementation = GameStoreDTO.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "Video Game already exists") })
    @PostMapping(value = "/save",  produces = { "application/json", "application/xml" })
    public ResponseEntity<?> saveOrUpdateVideoGame(@Parameter(description="Video Game to add. Cannot null or empty.", 
            required=true, schema=@Schema(implementation = GameStoreDTO.class))
            @Valid @RequestBody GameStoreDTO gameStoreDTO){
    	//GameStore gameUpdated = 
    			gameStoreService.
    			saveOrUpdateVideoGame(VideoGameTransformer.
    					map(sanitizingInput(gameStoreDTO), GameStore.class));
           
    	return new ResponseEntity<String>("VideoGame added successfully", HttpStatus.OK);
    }
    
    @Operation(summary = "Deletes a Video Game", description = "", tags = { "Video Games" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "Video Game not found") })
    @DeleteMapping(path="/delete/{videoGameIdx}")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<?> deleteVideoGameByVideoGameIdx(@Parameter(description="videoGameIdx of the Video Game to be delete. Cannot be empty.",
     required=true)
    @PathVariable long videoGameIdx) {
    	gameStoreService.deleteVideoGameById(videoGameIdx);
        return new ResponseEntity("VideoGame deleted successfully", HttpStatus.OK);
    }
    
    private GameStoreDTO sanitizingInput(GameStoreDTO input) {
    	GameStoreDTO newInput = new GameStoreDTO();
    	PlatformDTO newPlatform = new PlatformDTO();
    	
    	//game input
    	newInput.setDescription(validateInput(input.getDescription()));
    	newInput.setDevelopedBy(validateInput(input.getDevelopedBy()));
    	newInput.setTitle(validateInput(input.getTitle()));
    	newInput.setPlayers(input.getPlayers());
    	newInput.setVideoGameIdx(input.getVideoGameIdx());
    	newInput.setYear(input.getYear());
    	
    	//platform
    	newPlatform.setCountry(validateInput(input.getPlatform().getCountry()));
    	newPlatform.setName(validateInput(input.getPlatform().getName()));
    	newPlatform.setSystem(validateInput(input.getPlatform().getSystem()));
    	newInput.setPlatform(newPlatform);
    	return newInput;
    }
    
    private String validateInput(String str){
    	String data = null;
        if (str != null && str.length() > 0) {
          str = str.replace("\\", "\\\\");
          str = str.replace("'", "\\'");
          str = str.replace("\0", "\\0");
          str = str.replace("\n", "\\n");
          str = str.replace("\r", "\\r");
          str = str.replace("\"", "\\\"");
          str = str.replace("\\x1a", "\\Z");
          data = str;
        }
        return data;
    }
}
