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
public class Invoice {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long invoice_id;
        private long customer_id;
        private Date invoice_date;
        private Date due_date;
        private double total_amount;
        private double total_btw;
        private String status;

}
