package org.vti.vtibackend.DAL.Interface;

import org.vti.vtibackend.DAL.Entity.InvoiceItem;

import java.util.List;

public interface IInvoiceItemDAL {
    InvoiceItem save(InvoiceItem invoiceItem);
    List<InvoiceItem> findAll();


}
