package org.vti.vtibackend.DAL.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vti.vtibackend.DAL.Entity.Invoice;

import java.util.List;


public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query("SELECT MAX(i.invoice_number) FROM Invoice i")
    int findHighestInvoiceNumber();

    @Query("SELECT COUNT(i) FROM Invoice i WHERE i.status = 'Open'")
    int countOpenInvoices();

    @Query("SELECT i.invoice_date, SUM(i.total_amount) FROM Invoice i WHERE YEAR(i.invoice_date) = :year AND i.status = 'Betaald' GROUP BY i.invoice_date")
    List<Object[]> findInvoicesByYear(@Param("year") int year);
}
