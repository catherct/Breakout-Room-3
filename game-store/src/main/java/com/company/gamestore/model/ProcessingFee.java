package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "processing_fee")
public class ProcessingFee {

    @Id
    @Column(name = "product_type")
    @NotNull
    @Size(max=20)
    private String productType;

    @NotNull
    @Digits(integer=4, fraction=2)
    private BigDecimal fee;

    public String getProductType() {
        return productType;
    }


    public void setProductType(String productType) {
        this.productType = productType;


    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return productType.equals(that.productType) && fee.equals(that.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, fee);
    }

    @Override
    public String toString() {
        return "ProcessingFee{" +
                "product_type='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }
}
