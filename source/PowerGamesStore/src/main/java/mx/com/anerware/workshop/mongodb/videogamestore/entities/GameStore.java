package mx.com.anerware.workshop.mongodb.videogamestore.entities;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class GameStore {
	
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	public Long videoGameIdx;
	public String title;
	public String description;
	public String developedBy;
	public Integer year;
	public Platform platform;
	public Integer players;
}
