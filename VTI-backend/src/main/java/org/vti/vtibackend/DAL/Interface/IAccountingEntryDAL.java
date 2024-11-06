package org.vti.vtibackend.DAL.Interface;

import org.vti.vtibackend.DAL.Entity.AccountingEntry;

import java.util.List;

public interface IAccountingEntryDAL {
    AccountingEntry save(AccountingEntry entry);
    List<AccountingEntry> findAll();
    AccountingEntry getAccountingEntry(int accountingEntryId);

}
