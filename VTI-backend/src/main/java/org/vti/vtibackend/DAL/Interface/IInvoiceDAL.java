package org.vti.vtibackend.DAL.Interface;

import org.vti.vtibackend.DAL.Entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface IInvoiceDAL {
    Invoice save(Invoice invoice);
    List<Invoice> findAll();
    Optional<Invoice> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
}
