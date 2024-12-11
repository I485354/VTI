package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.AccountingEntry;
import org.vti.vtibackend.BLL.Interface.IAccountingEntryDAL;
import org.vti.vtibackend.DAL.Mapper.AccountingEntryMapper;
import org.vti.vtibackend.DAL.Repository.AccountingEntryRepo;
import org.vti.vtibackend.model.Accounting.AccountingentryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountingEntryDAL implements IAccountingEntryDAL {

    private final AccountingEntryRepo accountingEntryRepo;
    private final AccountingEntryMapper accountingEntryMapper;

    @Autowired
    public AccountingEntryDAL(AccountingEntryRepo accountingEntryRepo, AccountingEntryMapper accountingEntryMapper) {
        this.accountingEntryRepo = accountingEntryRepo;
        this.accountingEntryMapper = accountingEntryMapper;
    }

    public AccountingentryDTO getAccountingEntry(int accountingEntryId) {
        return accountingEntryRepo.findById(accountingEntryId)
                .map(accountingEntryMapper::ToDTO)
                .orElseThrow(() -> new RuntimeException("Accounting Entry not found with id: " + accountingEntryId));
    }

    public List<AccountingentryDTO> findAll() {
        return accountingEntryRepo.findAll()
                .stream()
                .map(accountingEntryMapper::ToDTO)
                .collect(Collectors.toList());
    }


    public AccountingentryDTO save(AccountingentryDTO accountingEntryDTO){
        AccountingEntry entry = accountingEntryMapper.ToEntity(accountingEntryDTO);
        AccountingEntry savedEntry = accountingEntryRepo.save(entry);
        return accountingEntryMapper.ToDTO(savedEntry);
    }

}
