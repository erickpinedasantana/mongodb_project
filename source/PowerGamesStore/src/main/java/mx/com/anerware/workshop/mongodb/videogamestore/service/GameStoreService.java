package mx.com.anerware.workshop.mongodb.videogamestore.service;

import java.util.List;

import mx.com.anerware.workshop.mongodb.videogamestore.entities.GameStore;

public interface GameStoreService {
	List<GameStore> findAll();
	GameStore findByVideoGameIdx(long videoGameIdx);
	List<GameStore> findByYear(int year);
    List<GameStore> findByDevelopedBy(String developedBy);
    GameStore saveOrUpdateVideoGame(GameStore videoGame);
    void deleteVideoGameById(long id);
    List<GameStore> findRecordBetweenYear(int initYear, int endingYear);
}
