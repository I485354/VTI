package org.vti.vtibackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoice_id;
    private long customer_id;
    private Date invoice_date;
    private Date due_date;
    private double total_amount;
    private double total_btw;
    private String status;

    // Getters en Setters
    public long getId() {
        return invoice_id;
    }

    public void setId(long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public Date getInvoice_date() {
        return invoice_date;
    }
    public void setInvoice_date(Date invoice_date) {
        this.invoice_date = invoice_date;
    }
    public Date getDue_date() {
        return due_date;
    }
    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }
    public double getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }
    public double getTotal_btw() {
        return total_btw;
    }
    public void setTotal_btw(Double total_btw) {
        this.total_btw = total_btw;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}