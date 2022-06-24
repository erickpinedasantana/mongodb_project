package mx.com.anerware.workshop.mongodb.videogamestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.anerware.workshop.mongodb.videogamestore.entities.GameStore;
import mx.com.anerware.workshop.mongodb.videogamestore.repository.GameStoreRepository;

@Service
public class GameStoreServiceImpl implements GameStoreService{

	@Autowired
	private GameStoreRepository videoGameRepository;
	
	@Override
	public List<GameStore> findAll() {
		return videoGameRepository.findAll();
	}

	@Override
	public GameStore findByVideoGameIdx(long videoGameIdx) {
		return videoGameRepository.findByVideoGameIdx(videoGameIdx);
	}

	@Override
	public List<GameStore> findByYear(int year) {
		return videoGameRepository.findByYear(year);
	}

	@Override
	public List<GameStore> findByDevelopedBy(String developedBy) {
		return videoGameRepository.findByDevelopedBy(developedBy);
	}

	@Override
	public GameStore saveOrUpdateVideoGame(GameStore videoGame) {
		return videoGameRepository.saveOrUpdateVideoGame(videoGame);
	}

	@Override
	public void deleteVideoGameById(long id) {
		videoGameRepository.deleteVideoGameById(id);
	}

	@Override
	public List<GameStore> findRecordBetweenYear(int initYear, int endingYear) {
		return videoGameRepository.findRecordBetweenYear(initYear, endingYear);
	}
}
