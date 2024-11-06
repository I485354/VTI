package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.InvoiceitemDTO;

import java.util.List;

public interface IInvoiceItemService {
    InvoiceitemDTO createItems(InvoiceitemDTO item);
    List<InvoiceitemDTO> getAllItems();
}
