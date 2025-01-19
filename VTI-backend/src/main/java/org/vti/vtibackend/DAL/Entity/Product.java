package org.vti.vtibackend.DAL.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int btw;

    public Product() {
    }


    public int getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(final int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public int getBtw() {
        return this.btw;
    }

    public void setBtw(final int btw) {
        this.btw = btw;
    }
}
