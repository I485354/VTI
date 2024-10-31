package org.vti.vtibackend.DAL.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoice_item_id;
    private int invoice_id;
    private int product_id;
    private int quantity;
    private double unit_price;
    private double btw;
    private double total;
}
