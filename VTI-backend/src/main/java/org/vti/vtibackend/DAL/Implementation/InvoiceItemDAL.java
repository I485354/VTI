package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.InvoiceItem;
import org.vti.vtibackend.BLL.Interface.IInvoiceItemDAL;
import org.vti.vtibackend.DAL.Mapper.InvoiceItemMapper;
import org.vti.vtibackend.DAL.Repository.InvoiceitemRepo;
import org.vti.vtibackend.model.InvoiceItem.InvoiceitemDTO;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InvoiceItemDAL implements IInvoiceItemDAL {

    private final InvoiceitemRepo invoiceitemRepo;
    private final InvoiceItemMapper invoiceitemMapper;

    @Autowired
    public InvoiceItemDAL(InvoiceitemRepo invoiceitemRepo, InvoiceItemMapper invoiceitemMapper) {
        this.invoiceitemRepo = invoiceitemRepo;
        this.invoiceitemMapper = invoiceitemMapper;
    }

    @Override
    public InvoiceitemDTO save(InvoiceitemDTO itemDTO) {
        InvoiceItem item = invoiceitemMapper.ToEntity(itemDTO);
        InvoiceItem savedItem = invoiceitemRepo.save(item);
        return invoiceitemMapper.ToDTO(savedItem);
    }

    @Override
    public List<InvoiceitemDTO> findAll(){
        return invoiceitemRepo.findAll()
                .stream()
                .map(invoiceitemMapper::ToDTO)
                .collect(Collectors.toList());
    }

}
