package com.company.gamestore.viewmodel;

import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {
    private Integer id;
    private String title;
    private String esrbRating;
    private String description;
    private BigDecimal price;
    private String studio;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public GameViewModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GameViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public GameViewModel setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public GameViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getStudio() {
        return studio;
    }

    public GameViewModel setStudio(String studio) {
        this.studio = studio;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public GameViewModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameViewModel that = (GameViewModel) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getEsrbRating(), that.getEsrbRating()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getStudio(), that.getStudio()) && Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getEsrbRating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }
}
