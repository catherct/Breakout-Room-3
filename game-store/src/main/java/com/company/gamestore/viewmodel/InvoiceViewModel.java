package com.company.gamestore.viewmodel;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {
    private int id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String itemType;
    private int itemId;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processingFee;
    private BigDecimal total;

    public int getId() {
        return id;
    }

    public InvoiceViewModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public InvoiceViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public InvoiceViewModel setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getCity() {
        return city;
    }

    public InvoiceViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public InvoiceViewModel setState(String state) {
        this.state = state;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public InvoiceViewModel setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public String getItemType() {
        return itemType;
    }

    public InvoiceViewModel setItemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public int getItemId() {
        return itemId;
    }

    public InvoiceViewModel setItemId(int itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public InvoiceViewModel setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public InvoiceViewModel setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public InvoiceViewModel setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public InvoiceViewModel setTax(BigDecimal tax) {
        this.tax = tax;
        return this;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public InvoiceViewModel setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public InvoiceViewModel setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getId() == that.getId() && getItemId() == that.getItemId() && getQuantity() == that.getQuantity() && Objects.equals(getName(), that.getName()) && Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getState(), that.getState()) && Objects.equals(getZipcode(), that.getZipcode()) && Objects.equals(getItemType(), that.getItemType()) && Objects.equals(getUnitPrice(), that.getUnitPrice()) && Objects.equals(getSubtotal(), that.getSubtotal()) && Objects.equals(getTax(), that.getTax()) && Objects.equals(getProcessingFee(), that.getProcessingFee()) && Objects.equals(getTotal(), that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStreet(), getCity(), getState(), getZipcode(), getItemType(), getItemId(), getUnitPrice(), getQuantity(), getSubtotal(), getTax(), getProcessingFee(), getTotal());
    }
}
