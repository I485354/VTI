package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.InvoiceDTO;

import java.util.List;

public interface IInvoiceService {
    List<InvoiceDTO> getAllInvoices();
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO updateStatus(Long id, String status);
}
