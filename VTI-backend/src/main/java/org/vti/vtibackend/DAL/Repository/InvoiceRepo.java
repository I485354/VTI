package org.vti.vtibackend.DAL.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.vti.vtibackend.DAL.Entity.Invoice;


public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query("SELECT MAX(i.invoice_number) FROM Invoice i")
    int findHighestInvoiceNumber();
}
