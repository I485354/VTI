package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.DAL.Entity.InvoiceItem;

public interface InvoiceitemRepo extends JpaRepository<InvoiceItem, Integer> {
}