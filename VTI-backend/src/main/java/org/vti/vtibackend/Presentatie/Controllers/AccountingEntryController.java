package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Service.AccountingEntryService;
import org.vti.vtibackend.model.Accountingentries;

import java.util.List;

@RestController
@RequestMapping("/api/accountingentries")
public class AccountingEntryController {
    @Autowired
    private AccountingEntryService accountingEntryService;

    @GetMapping
    public List<Accountingentries> getAllEntries() {
        return accountingEntryService.getAllEntries();
    }
    @PostMapping
    public Accountingentries createEntries(@RequestBody Accountingentries accountingEntries) {
        return accountingEntryService.createNewEntries(accountingEntries);
    }


}