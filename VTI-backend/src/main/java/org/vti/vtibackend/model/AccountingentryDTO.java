package org.vti.vtibackend.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountingentryDTO {
    private long entry_id;
    private int invoice_id;
    private Date entry_date;
    private double debit_amount;
    private double credit_amount;
    private String descriptions;

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public long getEntry_id() {
        return this.entry_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public int getInvoice_id() {
        return this.invoice_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public Date getEntry_date() {
        return this.entry_date;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public double getDebit_amount() {
        return this.debit_amount;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public double getCredit_amount() {
        return this.credit_amount;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getDescriptions() {
        return this.descriptions;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setEntry_id(final long entry_id) {
        this.entry_id = entry_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setInvoice_id(final int invoice_id) {
        this.invoice_id = invoice_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setEntry_date(final Date entry_date) {
        this.entry_date = entry_date;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setDebit_amount(final double debit_amount) {
        this.debit_amount = debit_amount;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setCredit_amount(final double credit_amount) {
        this.credit_amount = credit_amount;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setDescriptions(final String descriptions) {
        this.descriptions = descriptions;
    }

}