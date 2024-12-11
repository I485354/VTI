package org.vti.vtibackend.model.InvoiceItem;





public class InvoiceitemDTO {
    private int invoice_item_id;
    private int invoice_id;
    private int product_id;
    private int quantity;
    private double unit_price;
    private double btw;
    private double total;

    public InvoiceitemDTO() {}

    public InvoiceitemDTO(int invoice_item_id,int invoice_id, int product_id, int quantity, double unit_price, double btw, double total) {
        this.invoice_item_id = invoice_item_id;
        this.invoice_id = invoice_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.btw = btw;
        this.total = total;

    }
    public int getInvoice_item_id() {
        return this.invoice_item_id;
    }


    public int getInvoice_id() {
        return this.invoice_id;
    }

    public int getProduct_id() {
        return this.product_id;
    }

    public int getQuantity() {
        return this.quantity;
    }


    public double getUnit_price() {
        return this.unit_price;
    }

    public double getBtw() {
        return this.btw;
    }


    public double getTotal() {
        return this.total;
    }


    public void setInvoice_item_id(final int invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }


    public void setInvoice_id(final int invoice_id) {
        this.invoice_id = invoice_id;
    }


    public void setProduct_id(final int product_id) {
        this.product_id = product_id;
    }


    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }


    public void setUnit_price(final double unit_price) {
        this.unit_price = unit_price;
    }


    public void setBtw(final double btw) {
        this.btw = btw;
    }


    public void setTotal(final double total) {
        this.total = total;
    }
}
