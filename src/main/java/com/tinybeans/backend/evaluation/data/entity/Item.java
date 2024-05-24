package com.tinybeans.backend.evaluation.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author danrodrigues
 * Date: 13/5/22
 */
@Entity @Data @ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "item")
public class Item extends BaseEntity{

    private String name, description, photoUrl;

    private Double price;

    @ManyToMany(mappedBy = "items")
    @JsonBackReference
    private List<Orders> orders;

    public Double getPrice() {
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
