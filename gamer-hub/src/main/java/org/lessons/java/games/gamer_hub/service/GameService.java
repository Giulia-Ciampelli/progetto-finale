package org.lessons.java.games.gamer_hub.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.games.gamer_hub.exception.IdNotFoundException;
import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.model.OnSale;
import org.lessons.java.games.gamer_hub.repository.GameRepository;
import org.lessons.java.games.gamer_hub.repository.OnSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private OnSaleRepository saleRepository;

    // index
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    // show
    public Game getById(int id) {
        Optional<Game> gameAttempt = gameRepository.findById(id);

        if (gameAttempt.isEmpty()) {
            throw new IdNotFoundException("404: Game with id " + id + " not found.");
        }

        return gameAttempt.get();
    }

    // show per rest controller
    public Optional<Game> findById(int id) {
        return gameRepository.findById(id);
    }

    // ricerche personalizzate
    public List<Game> findByName(String name) {
        return gameRepository.findByNameContainingIgnoreCase(name);
    }

    // create
    public Game create(Game formGame) {
        return gameRepository.save(formGame);
    }

    // update
        public Game update(Game formGame) {
        return gameRepository.save(formGame);
    }

    // delete
    public void deleteById(int id) {
        Game game = getById(id);

        for (OnSale saleToDelete : game.getSales()) {
            saleRepository.delete(saleToDelete);
        }

        gameRepository.delete(game);
    }

    public void delete(Game game) {
        for (OnSale saleToDelete : game.getSales()) {
            saleRepository.delete(saleToDelete);
        }

        gameRepository.delete(game);
    }
}
