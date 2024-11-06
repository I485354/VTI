package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.IInvoiceItemService;
import org.vti.vtibackend.BLL.Mapper.InvoiceItemMapper;
import org.vti.vtibackend.DAL.Entity.InvoiceItem;
import org.vti.vtibackend.DAL.Interface.IInvoiceItemDAL;
import org.vti.vtibackend.model.InvoiceitemDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceItemService implements IInvoiceItemService {

    private final IInvoiceItemDAL invoiceItemDAL;
    private final InvoiceItemMapper invoiceItemMapper;

    @Autowired
    public InvoiceItemService(InvoiceItemMapper invoiceItemMapper, IInvoiceItemDAL invoiceitemDAL) {
        this.invoiceItemMapper = invoiceItemMapper;
        this.invoiceItemDAL = invoiceitemDAL;
    }

    public List<InvoiceitemDTO> getAllItems(){
        List<InvoiceItem> Item = invoiceItemDAL.findAll();
        return Item.stream()
                .map(invoiceItemMapper::ToDTO)
                .collect(Collectors.toList());
    }

    public InvoiceitemDTO createItems(InvoiceitemDTO item){
        InvoiceItem invoiceItem = invoiceItemMapper.ToEntity(item);
        InvoiceItem savedItem = invoiceItemDAL.save(invoiceItem);
        return invoiceItemMapper.ToDTO(savedItem);
    }

}