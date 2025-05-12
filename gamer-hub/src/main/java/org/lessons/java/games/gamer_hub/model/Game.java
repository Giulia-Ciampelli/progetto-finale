package org.lessons.java.games.gamer_hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "The name must contain at least one non-whitespace character.")
    @Size(min = 3, max = 255, message = "The name must be between 3 and 255 characters.")
    private String name;

    @NotBlank(message = "The description must contain at least one non-whitespace character.")
    @Size(min = 10, max = 255, message = "The description must be between 10 and 255 characters.")
    private String description;

    @NotNull(message = "The price field cannot be empty.")
    @Min(value = 0, message = "The price cannot be negative.")
    private float price;

    // #region getter e setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // #endregion getter e setter
}
