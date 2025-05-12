package org.lessons.java.games.gamer_hub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "The name cannot be empty.")
    @Size(min = 3, message = "The name must be at least 3 characters long.")
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Game> games;

    // #region getter e setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    // #endregion getter e setter
}
