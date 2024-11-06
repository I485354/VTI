package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.AccountingentryDTO;

import java.util.List;

public interface IAccountingEntryService {
    List<AccountingentryDTO> getAllEntries();
    AccountingentryDTO getAccountingEntryById(int id);
    AccountingentryDTO createNewEntries(AccountingentryDTO entries);
}
