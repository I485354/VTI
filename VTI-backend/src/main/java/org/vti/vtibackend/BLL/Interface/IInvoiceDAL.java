package org.vti.vtibackend.BLL.Interface;


import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.model.InvoiceDTO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface IInvoiceDAL {
    InvoiceDTO save(InvoiceDTO invoiceDTO);
    List<InvoiceDTO> findAll();
    Optional<InvoiceDTO> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    int findHighestInvoiceNumber();
    int countOpenInvoices();
    List<InvoiceDTO> findInvoicesByYear(int year);
}
