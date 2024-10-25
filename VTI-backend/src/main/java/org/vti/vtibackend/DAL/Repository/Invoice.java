package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.model.Invoices;

public interface Invoice extends JpaRepository<Invoices, Integer> {
}
