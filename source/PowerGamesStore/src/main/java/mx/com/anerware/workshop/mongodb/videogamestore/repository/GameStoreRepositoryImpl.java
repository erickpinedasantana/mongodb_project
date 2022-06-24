package mx.com.anerware.workshop.mongodb.videogamestore.repository;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import mx.com.anerware.workshop.mongodb.videogamestore.entities.GameStore;

@Repository
public class GameStoreRepositoryImpl implements GameStoreRepository{
	
	//trasaction for multiple inserts
	private static final TransactionOptions txnOptions 
	         = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
	
	//Mongo db client
	private final MongoClient client;
	private MongoCollection<GameStore> videoGameCollection;
	
	public GameStoreRepositoryImpl(MongoClient client) {
		this.client=client;
	}
	
	@PostConstruct
    void init() {
		videoGameCollection = client.getDatabase("VideoGamesDB").
        		getCollection("videoGames", GameStore.class);
    }

	@Override
	public GameStore findByVideoGameIdx(long videoGameIdx) {
		return videoGameCollection.find(eq("videoGameIdx",videoGameIdx)).first();
	}

	@Override
	public List<GameStore> findByYear(int year) {
		return videoGameCollection.find(eq("year",year))
				                  .into(new ArrayList<>());
	}

	@Override
	public List<GameStore> findByDevelopedBy(String developedBy) {
		return videoGameCollection.find(eq("developedBy",developedBy))
                .into(new ArrayList<>());
	}

	@Override
	public List<GameStore> findAll() {
		return videoGameCollection.find().into(new ArrayList<>());
	}

	@Override
	public GameStore saveOrUpdateVideoGame(GameStore videoGame) {
		videoGame.setId(new ObjectId());
		videoGameCollection.insertOne(videoGame);
		return videoGame;
	}

	@Override
	public long deleteVideoGameById(long videoGameIdx) {
		return videoGameCollection.
				deleteOne(eq("videoGameIdx", videoGameIdx)).getDeletedCount();
	}
	
	/*Querys advance*/
	@Override
	public List<GameStore> findRecordBetweenYear(int initYear, int endingYear) {
		BasicDBObject query = new BasicDBObject();
		BasicDBObject range = new BasicDBObject();
		range.append("$gte",initYear);
		range.append("$lte",endingYear);
		query.append("year", range);		
		return videoGameCollection.find(query).into(new ArrayList<>());
	}
}
