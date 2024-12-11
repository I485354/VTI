package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.InvoiceItem.InvoiceitemDTO;

import java.util.List;

public interface IInvoiceItemDAL {
    InvoiceitemDTO save(InvoiceitemDTO invoiceItemDTO);
    List<InvoiceitemDTO> findAll();


}
