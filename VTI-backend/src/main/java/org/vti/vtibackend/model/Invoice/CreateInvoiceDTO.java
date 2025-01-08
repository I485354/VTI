package org.vti.vtibackend.model.Invoice;



import java.util.Date;

public class CreateInvoiceDTO {
    private int customer_id;
    private Integer car_id;
    private Date invoice_date;
    private Date due_date;
    private double total_amount;
    private double total_btw;
    private String status;

    public CreateInvoiceDTO() {}
    public CreateInvoiceDTO(int customer_id, Integer car_id, Date invoice_date, Date due_date, double total_amount, double total_btw, String status) {
        this.customer_id = customer_id;
        this.car_id = car_id;
        this.invoice_date = invoice_date;
        this.due_date = due_date;
        this.total_amount = total_amount;
        this.total_btw = total_btw;
        this.status = status;

    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public Integer getCar_id() {
        return car_id;
    }
    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
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
    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
    public double getTotal_btw() {
        return total_btw;
    }
    public void setTotal_btw(double total_btw) {
        this.total_btw = total_btw;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
