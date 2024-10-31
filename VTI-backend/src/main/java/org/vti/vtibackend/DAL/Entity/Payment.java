package org.vti.vtibackend.DAL.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long payment_id;
    private int invoice_id;
    private Date payment_date;
    private double amount;
    private String payment_method;

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public long getPayment_id() {
        return this.payment_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public int getInvoice_id() {
        return this.invoice_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public Date getPayment_date() {
        return this.payment_date;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public double getAmount() {
        return this.amount;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getPayment_method() {
        return this.payment_method;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setPayment_id(final long payment_id) {
        this.payment_id = payment_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setInvoice_id(final int invoice_id) {
        this.invoice_id = invoice_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setPayment_date(final Date payment_date) {
        this.payment_date = payment_date;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setAmount(final double amount) {
        this.amount = amount;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setPayment_method(final String payment_method) {
        this.payment_method = payment_method;
    }
}
