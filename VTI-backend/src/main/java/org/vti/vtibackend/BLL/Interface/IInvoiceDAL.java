package org.vti.vtibackend.BLL.Interface;


import org.vti.vtibackend.model.Invoice.InvoiceAndCustomerDTO;
import org.vti.vtibackend.model.Invoice.InvoiceDTO;
import org.vti.vtibackend.model.Invoice.InvoiceYearSummaryDTO;

import java.util.List;
import java.util.Optional;

public interface IInvoiceDAL {
    InvoiceDTO save(InvoiceDTO invoiceDTO);
    List<InvoiceDTO> findAll();
    Optional<InvoiceDTO> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    int findHighestInvoiceNumber();
    int countOpenInvoices();
    List<InvoiceYearSummaryDTO> findInvoicesByYear(int year);
    List<InvoiceAndCustomerDTO> findInvoices();
}
