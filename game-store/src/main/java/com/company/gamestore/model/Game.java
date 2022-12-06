package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="game")
public class Game implements Serializable {
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "You must enter a title")
    @Size(min = 1, max = 50, message = "Error: Size cannot be less than 1 character or be more than 50 characters")
    private String title;

    @NotNull(message = "You must enter an Esrb Rating")
    @Size(min = 1, max = 50, message = "Error: Size cannot be less than 1 character or be more than 50 characters")
    private String esrbRating;

    @NotNull(message = "You must enter a description")
    @Size(min = 1, max = 255, message = "Error: Size cannot be less than 1 character or be more than 255 characters")
    private String description;

    @NotNull(message = "You must enter a price")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
    @NotNull(message = "You must enter a studio")
    @Size(min = 1, max = 50, message = "Error: Size cannot be less than 1 character or be more than 50 characters")
    private String studio;
    @NotNull(message = "You must enter a quantity")
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public Game setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Game setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public Game setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Game setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Game setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getStudio() {
        return studio;
    }

    public Game setStudio(String studio) {
        this.studio = studio;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Game setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(getId(), game.getId()) && Objects.equals(getTitle(), game.getTitle()) && Objects.equals(getEsrbRating(), game.getEsrbRating()) && Objects.equals(getDescription(), game.getDescription()) && Objects.equals(getPrice(), game.getPrice()) && Objects.equals(getStudio(), game.getStudio()) && Objects.equals(getQuantity(), game.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getEsrbRating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
