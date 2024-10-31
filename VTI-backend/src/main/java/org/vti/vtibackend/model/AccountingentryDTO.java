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
public class AccountingentryDTO {
    private long entry_id;
    private int invoice_id;
    private Date entry_date;
    private double debit_amount;
    private double credit_amount;
    private String descriptions;

}