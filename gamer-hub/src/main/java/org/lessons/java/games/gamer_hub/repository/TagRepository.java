package org.lessons.java.games.gamer_hub.repository;

import java.util.List;

import org.lessons.java.games.gamer_hub.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    public List<Tag> findByNameContainingIgnoreCase(String name);
}
