package org.vti.vtibackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private long invoice_id;
    private long customer_id;
    private Date invoice_date;
    private Date due_date;
    private double total_amount;
    private double total_btw;
    private String status;
    // Getters en Setters
}