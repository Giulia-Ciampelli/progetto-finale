package org.lessons.java.games.gamer_hub.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sales")
public class OnSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "The title must contain at least one non-whitespace character.")
    @Size(min = 3, max = 255, message = "The title must be between 3 and 255 characters.")
    private String title;

    @NotNull(message = "The sale start date cannot be empty.")
    @FutureOrPresent(message = "The sale start date cannot be in the past.")
    private LocalDate startDate;

    @NotNull(message = "The sale finish date cannot be empty.")
    @FutureOrPresent(message = "The sale finish date cannot be in the past.")
    private LocalDate finishDate;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @JsonBackReference
    private Game game;

    // #region getter e setter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public Game getGame() {
        return game;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    // #endregion getter e setter
}
