package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.DAL.Entity.AccountingEntry;
import org.vti.vtibackend.model.AccountingentryDTO;

import java.util.List;

public interface IAccountingEntryDAL {
    AccountingentryDTO save(AccountingentryDTO entryDTO);
    List<AccountingentryDTO> findAll();
    AccountingentryDTO getAccountingEntry(int accountingEntryId);

}
