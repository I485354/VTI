package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.DAL.Interface.IInvoiceDAL;
import org.vti.vtibackend.DAL.Repository.InvoiceRepo;

import java.util.List;
import java.util.Optional;

@Repository
public class InvoiceDAL implements IInvoiceDAL {
    private final InvoiceRepo invoiceRepo;

    @Autowired
    public InvoiceDAL(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepo.findAll();
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return invoiceRepo.existsById(id);
    }

    @Override
    public int findHighestInvoiceNumber(){
        return invoiceRepo.findHighestInvoiceNumber();
    }
}
