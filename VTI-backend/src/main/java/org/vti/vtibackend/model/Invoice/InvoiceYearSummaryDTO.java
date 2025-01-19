package org.vti.vtibackend.model.Invoice;


public class InvoiceYearSummaryDTO {
    private int year;               // Het jaar van de factuur
    private int quarter;            // Het kwartaal (1-4)
    private double total_amount;    // Totale omzet in dat kwartaal
    private int invoice_count;


    public InvoiceYearSummaryDTO() {
    }

    public InvoiceYearSummaryDTO(int year, int quarter, double total_amount, int invoice_count) {
        this.year = year;
        this.quarter = quarter;
        this.total_amount = total_amount;
        this.invoice_count = invoice_count;
    }

    public InvoiceYearSummaryDTO(int quarter, double total_amount, int invoice_count) {
        this.quarter = quarter;
        this.total_amount = total_amount;
        this.invoice_count = invoice_count;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public int getInvoice_count() {
        return invoice_count;

    }

    public void setInvoice_count(int invoice_count) {
        this.invoice_count = invoice_count;
    }

}
