package org.vti.vtibackend.DAL.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
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
public class Invoice {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long invoice_id;
        private long customer_id;
        private Integer car_id;
        private Date invoice_date;
        private Date due_date;
        private double total_amount;
        private double total_btw;
        private String status;
        private int invoice_number;

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public long getInvoice_id() {
                return this.invoice_id;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public long getCustomer_id() {
                return this.customer_id;
        }

        @java.lang.SuppressWarnings("all")
        public Integer getCar_id(){
                return this.car_id;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public Date getInvoice_date() {
                return this.invoice_date;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public Date getDue_date() {
                return this.due_date;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public double getTotal_amount() {
                return this.total_amount;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public double getTotal_btw() {
                return this.total_btw;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public String getStatus() {
                return this.status;
        }

        @java.lang.SuppressWarnings("all")
        public int getInvoice_number() {
                return this.invoice_number;
        }


        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public void setInvoice_id(final long invoice_id) {
                this.invoice_id = invoice_id;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public void setCustomer_id(final long customer_id) {
                this.customer_id = customer_id;
        }

        @java.lang.SuppressWarnings("all")
        public void setCar_id(final Integer car_id) {
                this.car_id = car_id;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public void setInvoice_date(final Date invoice_date) {
                this.invoice_date = invoice_date;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public void setDue_date(final Date due_date) {
                this.due_date = due_date;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public void setTotal_amount(final double total_amount) {
                this.total_amount = total_amount;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public void setTotal_btw(final double total_btw) {
                this.total_btw = total_btw;
        }

        @java.lang.SuppressWarnings("all")
        @lombok.Generated
        public void setStatus(final String status) {
                this.status = status;
        }

        @java.lang.SuppressWarnings("all")
        public void setInvoice_number(final int invoice_number) {
                this.invoice_number = invoice_number;
        }

}
