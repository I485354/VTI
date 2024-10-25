package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.model.Invoices;

import java.util.List;

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
}