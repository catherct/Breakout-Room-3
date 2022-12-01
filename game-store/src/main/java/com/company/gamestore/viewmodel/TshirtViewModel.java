package com.company.gamestore.viewmodel;

import java.math.BigDecimal;
import java.util.Objects;

public class TshirtViewModel {
    private Integer id;
    private String size;
    private String color;
    private String description;
    private BigDecimal price;
    private int quantity;

    public Integer getId() {
        return id;
    }

    public TshirtViewModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSize() {
        return size;
    }

    public TshirtViewModel setSize(String size) {
        this.size = size;
        return this;
    }

    public String getColor() {
        return color;
    }

    public TshirtViewModel setColor(String color) {
        this.color = color;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TshirtViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TshirtViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public TshirtViewModel setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TshirtViewModel that = (TshirtViewModel) o;
        return getQuantity() == that.getQuantity() && Objects.equals(getId(), that.getId()) && Objects.equals(getSize(), that.getSize()) && Objects.equals(getColor(), that.getColor()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
