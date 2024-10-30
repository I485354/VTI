package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.model.Invoices;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoices> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @PostMapping
    public Invoices createInvoice(@RequestBody Invoices invoices) {
        return invoiceService.createInvoice(invoices);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Invoices> updateInvoiceStatus(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        String status = updates.get("status");
        Invoices updatedInvoice = invoiceService.updateStatus(id, status);
        return ResponseEntity.ok(updatedInvoice);
    }
}