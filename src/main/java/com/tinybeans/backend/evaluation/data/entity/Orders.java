package com.tinybeans.backend.evaluation.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"paymentId"})
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity {

    @ManyToMany
    @JsonManagedReference
    private List<Item> items;

    @Column(scale = 2, precision = 5)
    private BigDecimal subTotal;

    @Column(scale = 2, precision = 5)
    private BigDecimal discount;

    @Column(scale = 2, precision = 5)
    private BigDecimal finalPrice;

    private String paymentId;

    // Constructors

    public Orders() {
        // Default constructor
    }

    // Getters and setters

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
