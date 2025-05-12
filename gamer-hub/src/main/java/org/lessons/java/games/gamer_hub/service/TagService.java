package org.lessons.java.games.gamer_hub.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.games.gamer_hub.exception.IdNotFoundException;
import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.model.Tag;
import org.lessons.java.games.gamer_hub.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    
    @Autowired
    private TagRepository tagRepository;

    // index
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    // show
    public Tag getById(int id) {
        Optional<Tag> tagAttempt = tagRepository.findById(id);

        if (tagAttempt.isEmpty()) {
            throw new IdNotFoundException("404: Tag with id '" + id + "' not found");
        }

        return tagAttempt.get();
    }

    // ricerche personalizzate
    public List<Tag> findByName(String name) {
        return tagRepository.findByNameContainingIgnoreCase(name);
    }

    // create
    public Tag create(Tag formTag) {
        return tagRepository.save(formTag);
    }

    // update
        public Tag update(Tag formTag) {
        return tagRepository.save(formTag);
    }

    // delete
    public void deleteById(int id) {
        Tag tag = getById(id);

        for (Game linkedGame : tag.getGames()) {
            linkedGame.getTags().remove(tag);
        }

        tagRepository.delete(tag);
    }

    public void delete(Tag tag) {
        for (Game linkedGame : tag.getGames()) {
            linkedGame.getTags().remove(tag);
        }

        tagRepository.delete(tag);
    }
}
