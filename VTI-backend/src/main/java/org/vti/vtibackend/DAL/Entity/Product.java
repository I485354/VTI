package org.vti.vtibackend.DAL.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    private String name;
    private String description;
    private double price;

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public int getProduct_id() {
        return this.product_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getName() {
        return this.name;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getDescription() {
        return this.description;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public double getPrice() {
        return this.price;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setProduct_id(final int product_id) {
        this.product_id = product_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setName(final String name) {
        this.name = name;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setDescription(final String description) {
        this.description = description;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setPrice(final double price) {
        this.price = price;
    }
}
