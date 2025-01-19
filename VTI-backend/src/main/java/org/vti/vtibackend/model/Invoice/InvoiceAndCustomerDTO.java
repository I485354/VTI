package org.vti.vtibackend.model.Invoice;


import org.vti.vtibackend.model.Customer.CustomerInfoDTO;

import java.util.Date;
import java.util.List;


public class InvoiceAndCustomerDTO {

    private int customer_id;
    private Date invoice_date;
    private Date due_date;
    private double total_amount;
    private double total_btw;
    private String status;
    private int invoice_number;
    private String deleted;
    private List<CustomerInfoDTO> customers;

    public InvoiceAndCustomerDTO() {
    }

    public InvoiceAndCustomerDTO(int customer_id, Date invoice_date, Date due_date, double total_amount, double total_btw, String status, int invoice_number, String deleted, List<CustomerInfoDTO> customers) {
        this.customer_id = customer_id;
        this.invoice_date = invoice_date;
        this.due_date = due_date;
        this.total_amount = total_amount;
        this.total_btw = total_btw;
        this.status = status;
        this.invoice_number = invoice_number;
        this.deleted = deleted;
        this.customers = customers;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
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

    public int getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(int invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public List<CustomerInfoDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerInfoDTO> customers) {
        this.customers = customers;
    }
}
