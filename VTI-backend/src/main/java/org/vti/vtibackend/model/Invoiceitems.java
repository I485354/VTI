package org.vti.vtibackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Invoiceitems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoice_item_id;
    private int invoice_id;
    private int product_id;
    private int quantity;
    private double unit_price;
    private double total;

    public long getInvoice_item_id() {
        return invoice_item_id;
    }
    public void setInvoice_item_id(long invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }
    public int getInvoice_id() {
        return invoice_id;
    }
    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnit_price() {
        return unit_price;
    }
    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
