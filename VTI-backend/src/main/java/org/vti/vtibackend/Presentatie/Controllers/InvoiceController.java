package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Interface.IInvoiceService;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.model.InvoiceDTO;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invoice")
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

    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoices) {
        return invoiceService.createInvoice(invoices);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InvoiceDTO> updateInvoiceStatus(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        String status = updates.get("status");
        InvoiceDTO updatedInvoice = invoiceService.updateStatus(id, status);
        return ResponseEntity.ok(updatedInvoice);
    }

}