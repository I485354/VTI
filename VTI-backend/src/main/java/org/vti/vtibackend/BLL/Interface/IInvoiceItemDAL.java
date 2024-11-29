package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.DAL.Entity.InvoiceItem;
import org.vti.vtibackend.model.InvoiceitemDTO;

import java.util.List;

public interface IInvoiceItemDAL {
    InvoiceitemDTO save(InvoiceitemDTO invoiceItemDTO);
    List<InvoiceitemDTO> findAll();


}
