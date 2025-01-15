package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.vti.vtibackend.BLL.Service.InvoiceItemService;
import org.vti.vtibackend.model.InvoiceItem.InvoiceitemDTO;

import java.util.List;

@RestController
@RequestMapping("/api/admin/invoiceitem")
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;

    @Autowired
    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }


    @GetMapping
    public List<InvoiceitemDTO> getInvoiceItems() {
        return invoiceItemService.getAllItems();
    }
    @PostMapping
    public InvoiceitemDTO getInvoiceItems(@RequestBody InvoiceitemDTO invoiceitems) {
        return invoiceItemService.createItems(invoiceitems);
    }
}
