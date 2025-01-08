package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.BLL.Interface.IInvoiceDAL;
import org.vti.vtibackend.DAL.Mapper.InvoiceMapper;
import org.vti.vtibackend.DAL.Repository.InvoiceRepo;

import org.vti.vtibackend.model.Customer.CustomerInfoDTO;
import org.vti.vtibackend.model.Invoice.InvoiceAndCustomerDTO;
import org.vti.vtibackend.model.Invoice.InvoiceDTO;
import org.vti.vtibackend.model.Invoice.InvoiceYearSummaryDTO;

import java.util.Arrays;
import java.util.Date;
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

    public List<InvoiceYearSummaryDTO> findInvoicesByYear(int year) {
        List<Object[]> rawResults = invoiceRepo.findInvoicesByYear(year);

        rawResults.forEach(result -> {
            System.out.println("Raw Result: " + Arrays.toString(result));
        });

        return rawResults.stream()
                .map(result -> {
                    InvoiceYearSummaryDTO invoiceGraph = new InvoiceYearSummaryDTO();
                    invoiceGraph.setYear(year);
                    invoiceGraph.setQuarter((Integer) result[0]);
                    invoiceGraph.setTotal_amount((Double) result[1]);
                    invoiceGraph.setInvoice_count(((Number) result[2]).intValue());
                    return invoiceGraph;
                })
                .collect(Collectors.toList());
    }

    public List<InvoiceAndCustomerDTO> findInvoices(){
        List<Object[]> results = invoiceRepo.findInvoices();


        return results.stream().map(result -> {
            InvoiceAndCustomerDTO dto = new InvoiceAndCustomerDTO();
            dto.setCustomer_id(((Number) result[0]).intValue());
            dto.setInvoice_date((Date) result[1]);
            dto.setDue_date((Date) result[2]);
            dto.setTotal_amount(((Number) result[3]).doubleValue());
            dto.setTotal_btw(((Number) result[4]).doubleValue());
            dto.setStatus((String) result[5]);
            dto.setInvoice_number(((Number) result[6]).intValue());
            dto.setDeleted((String) result[7]);

            CustomerInfoDTO customerInfo = new CustomerInfoDTO();
            customerInfo.setName((String) result[8]);
            customerInfo.setCompany((String) result[9]);
            customerInfo.setAddress((String) result[10]);
            customerInfo.setEmail((String) result[11]);
            customerInfo.setPhone((String) result[12]);
            customerInfo.setCustomer_number(((Number) result[13]).intValue());

            dto.setCustomers(List.of(customerInfo));

            return dto;
        }).collect(Collectors.toList());
    }
}
