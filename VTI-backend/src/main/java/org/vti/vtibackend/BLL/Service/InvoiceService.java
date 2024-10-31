package org.vti.vtibackend.BLL.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.DAL.Repository.InvoiceRepo;
import org.vti.vtibackend.model.InvoiceDTO;
import org.vti.vtibackend.BLL.Mapper.InvoiceMapper;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepo invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceService(InvoiceRepo invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(savedInvoice);
    }

    @Transactional
    public InvoiceDTO updateStatus(Long id, String status) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setStatus(status);
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(updatedInvoice);
    }

}
