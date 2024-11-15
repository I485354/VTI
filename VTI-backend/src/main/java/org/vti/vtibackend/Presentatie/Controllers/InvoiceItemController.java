package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Interface.IInvoiceItemService;
import org.vti.vtibackend.model.InvoiceitemDTO;

import java.util.List;

@RestController
@RequestMapping("/api/invoiceitem")
public class InvoiceItemController {

    private final IInvoiceItemService invoiceItemService;

    @Autowired
    public InvoiceItemController(IInvoiceItemService invoiceItemService) {
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
