package org.vti.vtibackend.DAL.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



import java.util.Date;
@Entity
public class Invoice {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int invoice_id;
        private int customer_id;
        private Integer car_id;
        private Date invoice_date;
        private Date due_date;
        private double total_amount;
        private double total_btw;
        private String status;
        private int invoice_number;
        private String deleted;

        public Invoice() {}

        public Invoice(int invoice_id, int customer_id, Integer car_id, Date invoice_date, Date due_date, double total_amount, double total_btw, String status, int invoice_number, String deleted) {
                this.invoice_id = invoice_id;
                this.customer_id = customer_id;
                this.car_id = car_id;
                this.invoice_date = invoice_date;
                this.due_date = due_date;
                this.total_amount = total_amount;
                this.total_btw = total_btw;
                this.status = status;
                this.invoice_number = invoice_number;
                this.deleted = deleted;
        }

        public int getInvoice_id() {
                return this.invoice_id;
        }

        public int getCustomer_id() {
                return this.customer_id;
        }

        public Integer getCar_id(){
                return this.car_id;
        }

        public Date getInvoice_date() {
                return this.invoice_date;
        }

        public Date getDue_date() {
                return this.due_date;
        }

        public double getTotal_amount() {
                return this.total_amount;
        }

        public double getTotal_btw() {
                return this.total_btw;
        }

        public String getStatus() {
                return this.status;
        }

        public int getInvoice_number() {
                return this.invoice_number;
        }

        public String getDeleted() {
                return this.deleted;
        }

        public void setInvoice_id(final int invoice_id) {
                this.invoice_id = invoice_id;
        }

        public void setCustomer_id(final int customer_id) {
                this.customer_id = customer_id;
        }

        public void setCar_id(final Integer car_id) {
                this.car_id = car_id;
        }

        public void setInvoice_date(final Date invoice_date) {
                this.invoice_date = invoice_date;
        }

        public void setDue_date(final Date due_date) {
                this.due_date = due_date;
        }

        public void setTotal_amount(final double total_amount) {
                this.total_amount = total_amount;
        }

        public void setTotal_btw(final double total_btw) {
                this.total_btw = total_btw;
        }

        public void setStatus(final String status) {
                this.status = status;
        }

        public void setInvoice_number(final int invoice_number) {
                this.invoice_number = invoice_number;
        }

        public void setDeleted(String deleted) {
                this.deleted = deleted;
        }
}
