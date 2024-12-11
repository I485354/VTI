package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.vti.vtibackend.BLL.Service.AccountingEntryService;
import org.vti.vtibackend.model.Accounting.AccountingentryDTO;

import java.util.List;

@RestController
@RequestMapping("/api/accountingentry")
public class AccountingEntryController {

    private final AccountingEntryService accountingEntryService;

    @Autowired
    public  AccountingEntryController(AccountingEntryService accountingEntryService) {
        this.accountingEntryService = accountingEntryService;
    }

    @GetMapping
    public List<AccountingentryDTO> getAllEntries() {
        return accountingEntryService.getAllEntries();
    }
    @PostMapping
    public AccountingentryDTO createEntries(@RequestBody AccountingentryDTO accountingEntries) {
        return accountingEntryService.createNewEntries(accountingEntries);
    }


}