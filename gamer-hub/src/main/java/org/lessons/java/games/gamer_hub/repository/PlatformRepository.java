package org.lessons.java.games.gamer_hub.repository;

import org.lessons.java.games.gamer_hub.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Integer> {
    
}
