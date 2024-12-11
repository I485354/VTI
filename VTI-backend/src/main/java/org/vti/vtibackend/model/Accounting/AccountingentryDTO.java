package org.vti.vtibackend.model.Accounting;




import java.util.Date;

public class AccountingentryDTO {
    private long entry_id;
    private long invoice_id;
    private Date entry_date;
    private double debit_amount;
    private double credit_amount;
    private String descriptions;

    public AccountingentryDTO() {}

    public AccountingentryDTO(long entry_id,long invoice_id, Date entry_date, double debit_amount,double credit_amount, String descriptions) {
        this.entry_id = entry_id;
        this.invoice_id = invoice_id;
        this.entry_date = entry_date;
        this.debit_amount = debit_amount;
        this.credit_amount = credit_amount;
        this.descriptions = descriptions;

    }

    public long getEntry_id() {
        return this.entry_id;
    }


    public long getInvoice_id() {
        return this.invoice_id;
    }


    public Date getEntry_date() {
        return this.entry_date;
    }


    public double getDebit_amount() {
        return this.debit_amount;
    }


    public double getCredit_amount() {
        return this.credit_amount;
    }


    public String getDescriptions() {
        return this.descriptions;
    }


    public void setEntry_id(final long entry_id) {
        this.entry_id = entry_id;
    }


    public void setInvoice_id(final long invoice_id) {
        this.invoice_id = invoice_id;
    }


    public void setEntry_date(final Date entry_date) {
        this.entry_date = entry_date;
    }


    public void setDebit_amount(final double debit_amount) {
        this.debit_amount = debit_amount;
    }


    public void setCredit_amount(final double credit_amount) {
        this.credit_amount = credit_amount;
    }


    public void setDescriptions(final String descriptions) {
        this.descriptions = descriptions;
    }

}