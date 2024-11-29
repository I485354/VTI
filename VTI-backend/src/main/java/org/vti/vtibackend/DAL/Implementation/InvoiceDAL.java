package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.BLL.Interface.IInvoiceDAL;
import org.vti.vtibackend.DAL.Mapper.InvoiceMapper;
import org.vti.vtibackend.DAL.Repository.InvoiceRepo;
import org.vti.vtibackend.model.InvoiceDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InvoiceDAL implements IInvoiceDAL {
    private final InvoiceRepo invoiceRepo;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceDAL(InvoiceRepo invoiceRepo, InvoiceMapper invoiceMapper) {
        this.invoiceRepo = invoiceRepo;
        this.invoiceMapper = invoiceMapper;
    }

    public InvoiceDTO save(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        Invoice savedInvoice = invoiceRepo.save(invoice);
        return invoiceMapper.toDTO(savedInvoice);
    }

    public List<InvoiceDTO> findAll() {
        return invoiceRepo.findAll()
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<InvoiceDTO> findById(Long id) {
        return invoiceRepo.findById(id)
                .map(invoiceMapper::toDTO);
    }

    public void deleteById(Long id) {
        invoiceRepo.deleteById(id);
    }

    public boolean existsById(Long id) {
        return invoiceRepo.existsById(id);
    }

    public int findHighestInvoiceNumber() {
        return invoiceRepo.findHighestInvoiceNumber();
    }

    public int countOpenInvoices() {
        return invoiceRepo.countOpenInvoices();
    }

    public List<InvoiceDTO> findInvoicesByYear(int year) {
        List<Object[]> results = invoiceRepo.findInvoicesByYear(year);

        return results.stream()
                .map(result -> {
                    InvoiceDTO dto = new InvoiceDTO();
                    dto.setInvoiceDate((java.util.Date) result[0]);
                    dto.setTotalAmount((Double) result[1]);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
