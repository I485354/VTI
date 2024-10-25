package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.DAL.Repository.Invoice;
import org.vti.vtibackend.model.Invoices;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private Invoice invoiceRepository;

    public List<Invoices> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoices createInvoice(Invoices invoices) {
        return invoiceRepository.save(invoices);
    }

}