package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.InvoiceItem;
import org.vti.vtibackend.DAL.Interface.IInvoiceItemDAL;
import org.vti.vtibackend.DAL.Repository.InvoiceitemRepo;

import java.util.List;

@Repository
public class InvoiceItemDAL implements IInvoiceItemDAL {

    private final InvoiceitemRepo invoiceitemRepo;

    @Autowired
    public InvoiceItemDAL(InvoiceitemRepo invoiceitemRepo) {
        this.invoiceitemRepo = invoiceitemRepo;
    }

    @Override
    public InvoiceItem save(InvoiceItem invoiceItem) {
        return invoiceitemRepo.save(invoiceItem);
    }

    @Override
    public List<InvoiceItem> findAll(){
        return invoiceitemRepo.findAll();
    }

}
