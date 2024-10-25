package org.vti.vtibackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_id;
    private long customer_id;
    private Date invoice_date;
    private Date due_date;
    private Double total_amount;
    private String status;

    // Getters en Setters
    public Long getId() {
        return invoice_id;
    }

    public void setId(Long invoice_id) {
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
    public Double getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}