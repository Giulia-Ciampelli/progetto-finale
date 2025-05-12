package org.lessons.java.games.gamer_hub.repository;

import org.lessons.java.games.gamer_hub.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
    
    // TODO: metti ricerca per nome dopo
}
