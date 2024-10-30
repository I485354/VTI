package org.vti.vtibackend.DAL.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.vti.vtibackend.model.Invoices;

public interface Invoice extends JpaRepository<Invoices, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Invoices i SET i.status = :status WHERE i.invoice_id = :id")
    int updateInvoiceStatus(Integer id, String status);
}
