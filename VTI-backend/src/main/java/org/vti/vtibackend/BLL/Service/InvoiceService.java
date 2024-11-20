package org.vti.vtibackend.BLL.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.IInvoiceService;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.DAL.Interface.IInvoiceDAL;
import org.vti.vtibackend.model.InvoiceDTO;
import org.vti.vtibackend.BLL.Mapper.InvoiceMapper;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements IInvoiceService {

    private final IInvoiceDAL invoiceDAL;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceService(InvoiceMapper invoiceMapper, IInvoiceDAL invoiceDAL ) {
        this.invoiceMapper = invoiceMapper;
        this.invoiceDAL = invoiceDAL;
    }

    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceDAL.findAll();
        return invoices.stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        if (invoiceDTO.getCar_id() == null) {
            invoice.setCar_id(null);
        }
        int highestInvoiceNumber = invoiceDAL.findHighestInvoiceNumber();
        int nextInvoiceNumber = (highestInvoiceNumber != 0) ? highestInvoiceNumber + 1 : 1;
        invoice.setInvoice_number(nextInvoiceNumber);
        Invoice savedInvoice = invoiceDAL.save(invoice);
        return invoiceMapper.toDTO(savedInvoice);
    }

    @Transactional
    public InvoiceDTO updateStatus(Long id, String status) {
        Invoice invoice = invoiceDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setStatus(status);
        Invoice updatedInvoice = invoiceDAL.save(invoice);
        return invoiceMapper.toDTO(updatedInvoice);
    }

}
