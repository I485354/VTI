package org.vti.vtibackend.BLL.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.BLL.Interface.IInvoiceDAL;
import org.vti.vtibackend.model.InvoiceDTO;
import org.vti.vtibackend.DAL.Mapper.InvoiceMapper;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService  {

    private final IInvoiceDAL invoiceDAL;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceService(InvoiceMapper invoiceMapper, IInvoiceDAL invoiceDAL ) {
        this.invoiceMapper = invoiceMapper;
        this.invoiceDAL = invoiceDAL;
    }

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceDAL.findAll();
    }

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        if (invoiceDTO.getCar_id() == null) {
            invoiceDTO.setCar_id(null);
        }

        int highestInvoiceNumber = invoiceDAL.findHighestInvoiceNumber();
        int nextInvoiceNumber = (highestInvoiceNumber != 0) ? highestInvoiceNumber + 1 : 1;
        invoiceDTO.setInvoice_number(nextInvoiceNumber);
        return invoiceDAL.save(invoiceDTO);
    }

    @Transactional
    public InvoiceDTO updateStatus(Long id, String status) {
        InvoiceDTO invoiceDTO = invoiceDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoiceDTO.setStatus(status);

        return invoiceDAL.save(invoiceDTO);
    }

    public int getOpenInvoicesCount() {
        return invoiceDAL.countOpenInvoices();
    }

    public List<InvoiceDTO> getInvoicesByYear(int year) {
        return invoiceDAL.findInvoicesByYear(year);
    }

}
/*
return results.stream()
                .map(result -> {
Date invoiceDate = (Date) result[0];
double totalAmount = (double) result[1];

InvoiceDTO dto = new InvoiceDTO();
                    dto.setInvoiceDate(invoiceDate);
                    dto.setTotalAmount(totalAmount);
                    return dto;
                })
                        .collect(Collectors.toList());*/
