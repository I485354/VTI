package org.vti.vtibackend.DAL.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.vti.vtibackend.DAL.Entity.Invoice;


public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Invoice i SET i.status = :status WHERE i.invoice_id = :id")
    int updateInvoiceStatus(Long id, String status);
}
