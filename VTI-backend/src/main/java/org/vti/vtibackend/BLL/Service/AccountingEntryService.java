package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.IAccountingEntryDAL;
import org.vti.vtibackend.model.Accounting.AccountingentryDTO;

import java.util.List;


@Service
public class AccountingEntryService {

    private final IAccountingEntryDAL accountingEntryDAL;

    @Autowired
    public AccountingEntryService(IAccountingEntryDAL accountingEntryDAL) {

        this.accountingEntryDAL = accountingEntryDAL;
    }

    public List<AccountingentryDTO> getAllEntries() {
        return accountingEntryDAL.findAll();
    }

    public AccountingentryDTO createNewEntries(AccountingentryDTO entries) {
        return accountingEntryDAL.save(entries);
    }

    public AccountingentryDTO getAccountingEntryById(int id) {
        return accountingEntryDAL.getAccountingEntry(id);
    }
}