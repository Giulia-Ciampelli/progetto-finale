package org.lessons.java.games.gamer_hub.repository;

import java.util.Optional;

import org.lessons.java.games.gamer_hub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
