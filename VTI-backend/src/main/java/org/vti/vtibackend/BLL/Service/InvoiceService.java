package org.vti.vtibackend.BLL.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.IInvoiceDAL;
import org.vti.vtibackend.model.Invoice.*;


import java.util.List;

@Service
public class InvoiceService  {

    private final IInvoiceDAL invoiceDAL;

    @Autowired
    public InvoiceService(IInvoiceDAL invoiceDAL ) {

        this.invoiceDAL = invoiceDAL;
    }

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceDAL.findAll();
    }

    public InvoiceDTO createInvoice(CreateInvoiceDTO createInvoiceDTO) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setCustomer_id(createInvoiceDTO.getCustomer_id());
        invoiceDTO.setCar_id(createInvoiceDTO.getCar_id());
        invoiceDTO.setInvoice_date(createInvoiceDTO.getInvoice_date());
        invoiceDTO.setDue_date(createInvoiceDTO.getDue_date());
        invoiceDTO.setTotal_amount(createInvoiceDTO.getTotal_amount());
        invoiceDTO.setTotal_btw(createInvoiceDTO.getTotal_btw());
        invoiceDTO.setStatus(createInvoiceDTO.getStatus());

        if (invoiceDTO.getCar_id() == null) {
            createInvoiceDTO.setCar_id(null);
        }
        if(invoiceDTO.getInvoice_date() == null
                || invoiceDTO.getDue_date() == null
                || invoiceDTO.getCustomer_id() == 0
                || invoiceDTO.getTotal_amount() == 0
                || invoiceDTO.getTotal_btw() == 0) {
           throw new IllegalArgumentException("some parameters are missing");
        } else {

            int highestInvoiceNumber = invoiceDAL.findHighestInvoiceNumber();
            int nextInvoiceNumber = (highestInvoiceNumber != 0) ? highestInvoiceNumber + 1 : 1;
            invoiceDTO.setInvoice_number(nextInvoiceNumber);
            return invoiceDAL.save(invoiceDTO);
        }
    }

    @Transactional
    public InvoiceDTO updateStatus(long id, UpdateInvoiceStatusDTO updateStatusDTO) {
        InvoiceDTO invoiceDTO = invoiceDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoiceDTO.setStatus(updateStatusDTO.getStatus());
        return invoiceDAL.save(invoiceDTO);
    }

    public int getOpenInvoicesCount() {
        return invoiceDAL.countOpenInvoices();
    }

    public List<InvoiceYearSummaryDTO> getInvoicesByYear(int year) {
        return invoiceDAL.findInvoicesByYear(year);
    }

    public List<InvoiceAndCustomerDTO> findInvoicesWithCustomer() {
        return invoiceDAL.findInvoices();
    }
}
