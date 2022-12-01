package com.company.gamestore.viewmodel;


import java.math.BigDecimal;
import java.util.Objects;

public class ConsoleViewModel {
    private Integer id;
    private String model;
    private String manufacturer;
    private String memory_amount;
    private String processor;
    private BigDecimal price;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public ConsoleViewModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ConsoleViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ConsoleViewModel setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getMemory_amount() {
        return memory_amount;
    }

    public ConsoleViewModel setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
        return this;
    }

    public String getProcessor() {
        return processor;
    }

    public ConsoleViewModel setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ConsoleViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ConsoleViewModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleViewModel that = (ConsoleViewModel) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getModel(), that.getModel()) && Objects.equals(getManufacturer(), that.getManufacturer()) && Objects.equals(getMemory_amount(), that.getMemory_amount()) && Objects.equals(getProcessor(), that.getProcessor()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModel(), getManufacturer(), getMemory_amount(), getProcessor(), getPrice(), getQuantity());
    }
}
