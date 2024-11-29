package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.IInvoiceItemDAL;
import org.vti.vtibackend.model.InvoiceitemDTO;

import java.util.List;


@Service
public class InvoiceItemService {

    private final IInvoiceItemDAL invoiceItemDAL;

    @Autowired
    public InvoiceItemService(IInvoiceItemDAL invoiceItemDAL) {
        this.invoiceItemDAL = invoiceItemDAL;
    }

    public List<InvoiceitemDTO> getAllItems(){
        List<InvoiceitemDTO> Item = invoiceItemDAL.findAll();
        return Item.stream().toList();
    }

    public InvoiceitemDTO createItems(InvoiceitemDTO item){
      return invoiceItemDAL.save(item);
    }

}