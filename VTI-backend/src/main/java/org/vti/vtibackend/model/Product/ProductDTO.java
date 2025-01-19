package org.vti.vtibackend.model.Product;


public class ProductDTO {

    private int product_id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int btw;

    public ProductDTO() {
    }

    public ProductDTO(int product_id, String name, String description, double price, int quantity, int btw) {
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBtw() {
        return this.btw;
    }

    public void setBtw(final int btw) {
        this.btw = btw;
    }

}
