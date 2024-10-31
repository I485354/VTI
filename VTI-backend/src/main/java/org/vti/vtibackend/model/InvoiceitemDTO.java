package org.vti.vtibackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceitemDTO {
    private int invoice_item_id;
    private int invoice_id;
    private int product_id;
    private int quantity;
    private double unit_price;
    private double btw;
    private double total;
    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public int getInvoice_item_id() {
        return this.invoice_item_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public int getInvoice_id() {
        return this.invoice_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public int getProduct_id() {
        return this.product_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public int getQuantity() {
        return this.quantity;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public double getUnit_price() {
        return this.unit_price;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public double getBtw() {
        return this.btw;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public double getTotal() {
        return this.total;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setInvoice_item_id(final int invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setInvoice_id(final int invoice_id) {
        this.invoice_id = invoice_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setProduct_id(final int product_id) {
        this.product_id = product_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setUnit_price(final double unit_price) {
        this.unit_price = unit_price;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setBtw(final double btw) {
        this.btw = btw;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setTotal(final double total) {
        this.total = total;
    }
}
