package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Mapper.InvoiceItemMapper;
import org.vti.vtibackend.DAL.Entity.InvoiceItem;
import org.vti.vtibackend.DAL.Repository.InvoiceitemRepo;
import org.vti.vtibackend.model.InvoiceitemDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceItemService {
    private final InvoiceitemRepo invoiceitemRepository;
    private final InvoiceItemMapper invoiceItemMapper;
    @Autowired
    public InvoiceItemService(InvoiceitemRepo invoiceitemRepository, InvoiceItemMapper invoiceItemMapper) {
        this.invoiceitemRepository = invoiceitemRepository;
        this.invoiceItemMapper = invoiceItemMapper;
    }

    public List<InvoiceitemDTO> getAllItems(){
        List<InvoiceItem> Item = invoiceitemRepository.findAll();
        return Item.stream()
                .map(invoiceItemMapper::ToDTO)
                .collect(Collectors.toList());
    }

    public InvoiceitemDTO createItems(InvoiceitemDTO item){
        InvoiceItem invoiceItem = invoiceItemMapper.ToEntity(item);
        InvoiceItem savedItem = invoiceitemRepository.save(invoiceItem);
        return invoiceItemMapper.ToDTO(savedItem);
    }

}