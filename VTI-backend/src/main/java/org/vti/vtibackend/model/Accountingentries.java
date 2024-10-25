package org.vti.vtibackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Accountingentries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long entry_Id;
    private int invoice_Id;
    private Date entry_date;
    private Double debit_amount;
    private Double credit_amount;
    private String description;

    public long getEntry_Id() {
        return entry_Id;
    }

    public void setEntry_Id(long entry_Id) {
        this.entry_Id = entry_Id;
    }

    public int getInvoice_Id() {
        return invoice_Id;
    }

    public void setInvoice_Id(int invoice_Id) {
        this.invoice_Id = invoice_Id;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public Double getDebit_amount() {
        return debit_amount;
    }

    public void setDebit_amount(Double debit_amount) {
        this.debit_amount = debit_amount;
    }

    public Double getCredit_amount() {
        return credit_amount;
    }

    public void setCredit_amount(Double credit_amount) {
        this.credit_amount = credit_amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}