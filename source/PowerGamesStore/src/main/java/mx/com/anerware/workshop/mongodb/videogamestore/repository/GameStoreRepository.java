package mx.com.anerware.workshop.mongodb.videogamestore.repository;

import java.util.List;

import mx.com.anerware.workshop.mongodb.videogamestore.entities.GameStore;

public interface GameStoreRepository {
	GameStore findByVideoGameIdx(long videoGameIdx);
	List<GameStore> findByYear(int year);
	List<GameStore> findByDevelopedBy(String developedBy);
	List<GameStore> findAll();
	GameStore saveOrUpdateVideoGame(GameStore videoGame);
    long deleteVideoGameById(long videoGameIdx);
    List<GameStore> findRecordBetweenYear(int initYear, int endingYear);
}
