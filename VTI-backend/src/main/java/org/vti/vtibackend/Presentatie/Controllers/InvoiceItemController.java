package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Service.InvoiceItemService;
import org.vti.vtibackend.model.Invoiceitems;

import java.util.List;

@RestController
@RequestMapping("/api/invoiceitems")
public class InvoiceItemController {
    @Autowired
    private InvoiceItemService invoiceItemService;

    @GetMapping
    public List<Invoiceitems> getInvoiceItems() {
        return invoiceItemService.getAllItems();
    }
    @PostMapping
    public Invoiceitems getInvoiceItems(@RequestBody Invoiceitems invoiceitems) {
        return invoiceItemService.createItems(invoiceitems);
    }
}
