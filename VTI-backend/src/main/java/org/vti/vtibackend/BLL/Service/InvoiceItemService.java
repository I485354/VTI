package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.DAL.Repository.Invoiceitem;
import org.vti.vtibackend.model.Invoiceitems;

import java.util.List;

@Service
public class InvoiceItemService {
    @Autowired
    private Invoiceitem invoiceitemRepository;

    public List<Invoiceitems> getAllItems(){
        return invoiceitemRepository.findAll();
    }

    public Invoiceitems createItems(Invoiceitems item){
        return invoiceitemRepository.save(item);
    }

}