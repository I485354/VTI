package org.vti.vtibackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long payment_id;
    private int invoice_id;
    private Date payment_date;
    private double amount;
    private String payment_method;

    public long getPayment_id() {
        return payment_id;
    }
    public void setPayment_id(long payment_id) {
        this.payment_id = payment_id;
    }
    public int getInvoice_id() {
        return invoice_id;
    }
    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }
    public Date getPayment_date() {
        return payment_date;
    }
    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getPayment_method() {
        return payment_method;
    }
    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
