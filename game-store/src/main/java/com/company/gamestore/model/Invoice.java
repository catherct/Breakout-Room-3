package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice implements Serializable {
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "You must enter a name")
    @Size(min = 1, max = 80, message = "Error: Size cannot be less than 1 character or be more than 50 characters")
    private String name;
    @NotNull(message = "You must enter a street")
    @Size(min = 1, max = 30, message = "Error: Size cannot be less than 1 character or be more than 30 characters")
    private String street;
    @NotNull(message = "You must enter a city")
    @Size(min = 1, max = 30, message = "Error: Size cannot be less than 1 character or be more than 30 characters")
    private String city;
    @NotNull(message = "You must enter a state")
    @Size(min = 1, max = 30, message = "Error: Size cannot be less than 1 character or be more than 2 characters")
    private String state;
    @NotNull(message = "You must enter a zipcode")
    @Size(min = 1, max = 5, message = "Error: Size cannot be less than 1 character or be more than 5 characters")
    private String zipcode;
    @NotNull(message = "You must enter an item type")
    @Size(min = 1, max = 20, message = "Error: Size cannot be less than 1 character or be more than 20 characters")
    private String itemType;

    @NotNull(message = "You must enter a item id")
    @Column(name = "item_id")
    private Integer itemId;

    @NotNull(message = "You must enter a unit price ")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal unitPrice;
    @NotNull(message = "You must enter a quantity")
    private Integer quantity;
    @NotNull(message = "You must enter a subtotal")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal subtotal;
    @NotNull(message = "You must enter a tax")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal tax;
    @NotNull(message = "You must enter a processing fee")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal processingFee;
    @NotNull(message = "You must enter a total")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return getId() == invoice.getId() && getItemId() == invoice.getItemId() && getQuantity() == invoice.getQuantity() && Objects.equals(getName(), invoice.getName()) && Objects.equals(getStreet(), invoice.getStreet()) && Objects.equals(getCity(), invoice.getCity()) && Objects.equals(getState(), invoice.getState()) && Objects.equals(getZipcode(), invoice.getZipcode()) && Objects.equals(getItemType(), invoice.getItemType()) && Objects.equals(getUnitPrice(), invoice.getUnitPrice()) && Objects.equals(getSubtotal(), invoice.getSubtotal()) && Objects.equals(getTax(), invoice.getTax()) && Objects.equals(getProcessingFee(), invoice.getProcessingFee()) && Objects.equals(getTotal(), invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStreet(), getCity(), getState(), getZipcode(), getItemType(), getItemId(), getUnitPrice(), getQuantity(), getSubtotal(), getTax(), getProcessingFee(), getTotal());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
