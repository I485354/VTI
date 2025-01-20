package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.model.Invoice.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @PostMapping("admin/invoice/create_invoice")
    public InvoiceDTO createInvoice(@RequestBody CreateInvoiceDTO invoices) {
        return invoiceService.createInvoice(invoices);
    }

    @PutMapping("admin/invoice/{id}/status")
    public ResponseEntity<InvoiceDTO> updateInvoiceStatus(@PathVariable Long id, @RequestBody UpdateInvoiceStatusDTO updateInvoiceStatusDTO) {
        InvoiceDTO updatedInvoice = invoiceService.updateStatus(id, updateInvoiceStatusDTO);
        return ResponseEntity.ok(updatedInvoice);
    }

    @GetMapping("admin/invoice/open-invoices")
    public ResponseEntity<Integer> getOpenInvoices() {
        int count = invoiceService.getOpenInvoicesCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("admin/invoice/revenue")
    public ResponseEntity<List<InvoiceYearSummaryDTO>> getInvoicesByYear(@RequestParam int year) {
        List<InvoiceYearSummaryDTO> invoices = invoiceService.getInvoicesByYear(year);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("admin/invoice/invoices-with-customers")
    public ResponseEntity<List<InvoiceAndCustomerDTO>> getInvoicesWithCustomers() {
        List<InvoiceAndCustomerDTO> invoiceAndCustomers = invoiceService.findInvoicesWithCustomer();
        return ResponseEntity.ok(invoiceAndCustomers);
    }

    @GetMapping("customer/invoice/{customer_id}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByCustomer(@PathVariable int customer_id) {
        List<InvoiceDTO> invoices = invoiceService.findInvoicesByCustomer(customer_id);
        return ResponseEntity.ok(invoices);
    }

}
