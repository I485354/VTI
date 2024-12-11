package org.vti.vtibackend.DAL.Entity;
import jakarta.persistence.*;

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

    public Product() {}
    public Product(int product_id, String name, String description, double price, int quantity, int btw) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.btw = btw;
    }
    public int getProduct_id() {
        return this.product_id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getBtw() {
        return this.btw;
    }

    public void setProduct_id(final int product_id) {
        this.product_id = product_id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public void setBtw(final int btw) {
        this.btw = btw;
    }
}
