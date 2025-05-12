package org.lessons.java.games.gamer_hub.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.games.gamer_hub.exception.IdNotFoundException;
import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.model.Platform;
import org.lessons.java.games.gamer_hub.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {
    
    @Autowired
    private PlatformRepository platformRepository;

    // index
    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    // show
    public Platform getById(int id) {
        Optional<Platform> platformAttempt = platformRepository.findById(id);

        if (platformAttempt.isEmpty()) {
            throw new IdNotFoundException("404: Platform with id '" + id + "' not found");
        }

        return platformAttempt.get();
    }

    // ricerche personalizzate
    public List<Platform> findByName(String name) {
        return platformRepository.findByNameContainingIgnoreCase(name);
    }

    // create
    public Platform create(Platform formPlatform) {
        return platformRepository.save(formPlatform);
    }

    // update
        public Platform update(Platform formPlatform) {
        return platformRepository.save(formPlatform);
    }

    // delete
    public void deleteById(int id) {
        Platform platform = getById(id);

        for (Game linkedGame : platform.getGames()) {
            linkedGame.getPlatforms().remove(platform);
        }

        platformRepository.delete(platform);
    }

    public void delete(Platform platform) {
        for (Game linkedGame : platform.getGames()) {
            linkedGame.getPlatforms().remove(platform);
        }

        platformRepository.delete(platform);
    }
}
