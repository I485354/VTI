package org.vti.vtibackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int product_id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int btw;

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
    public int getQuantity() {
        return this.quantity;
    }
    @java.lang.SuppressWarnings("all")
    public int getBtw() {
        return this.btw;
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

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @java.lang.SuppressWarnings("all")
    public void setBtw(final int btw) {
        this.btw = btw;
    }

}
