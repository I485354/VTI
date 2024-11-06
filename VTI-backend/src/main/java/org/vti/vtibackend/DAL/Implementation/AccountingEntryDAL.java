package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.AccountingEntry;
import org.vti.vtibackend.DAL.Interface.IAccountingEntryDAL;
import org.vti.vtibackend.DAL.Repository.AccountingEntryRepo;

import java.util.List;

@Repository
public class AccountingEntryDAL implements IAccountingEntryDAL {

    private final AccountingEntryRepo accountingEntryRepo;

    @Autowired
    public AccountingEntryDAL(AccountingEntryRepo accountingEntryRepo) {
        this.accountingEntryRepo = accountingEntryRepo;
    }

    @Override
    public AccountingEntry getAccountingEntry(int accountingEntryId) {
        return accountingEntryRepo.getReferenceById(accountingEntryId);
    }
    @Override
    public List<AccountingEntry> findAll() {
        return accountingEntryRepo.findAll();
    }

    @Override
    public AccountingEntry save(AccountingEntry accountingEntry){
        return accountingEntryRepo.save(accountingEntry);
    }

}
