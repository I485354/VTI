package org.vti.vtibackend.DAL.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;
    private int invoice_id;
    private Date payment_date;
    private double amount;
    private String payment_method;

    public Payment() {
    }


    public int getPayment_id() {
        return this.payment_id;
    }

    public void setPayment_id(final int payment_id) {
        this.payment_id = payment_id;
    }

    public int getInvoice_id() {
        return this.invoice_id;
    }

    public void setInvoice_id(final int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Date getPayment_date() {
        return this.payment_date;
    }

    public void setPayment_date(final Date payment_date) {
        this.payment_date = payment_date;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public String getPayment_method() {
        return this.payment_method;
    }

    public void setPayment_method(final String payment_method) {
        this.payment_method = payment_method;
    }
}
