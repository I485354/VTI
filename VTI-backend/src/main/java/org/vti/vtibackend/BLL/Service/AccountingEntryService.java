package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.IAccountingEntryService;
import org.vti.vtibackend.BLL.Mapper.AccountingEntryMapper;
import org.vti.vtibackend.DAL.Entity.AccountingEntry;
import org.vti.vtibackend.DAL.Interface.IAccountingEntryDAL;
import org.vti.vtibackend.model.AccountingentryDTO;
import org.vti.vtibackend.DAL.Repository.AccountingEntryRepo;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountingEntryService implements IAccountingEntryService{

    private final IAccountingEntryDAL accountingEntryDAL;
    private final AccountingEntryMapper accountingEntryMapper;
    @Autowired
    public AccountingEntryService(AccountingEntryMapper accountingEntryMapper, IAccountingEntryDAL accountingEntryDAL) {
        this.accountingEntryMapper = accountingEntryMapper;
        this.accountingEntryDAL = accountingEntryDAL;
    }

    public List<AccountingentryDTO> getAllEntries() {
        List<AccountingEntry> entries = accountingEntryDAL.findAll();
        return entries.stream()
                .map(accountingEntryMapper::ToDTO)
                .collect(Collectors.toList());
    }
    public AccountingentryDTO createNewEntries(AccountingentryDTO entries) {
        AccountingEntry entry = accountingEntryMapper.ToEntity(entries);
        AccountingEntry savedEntry = accountingEntryDAL.save(entry);
        return accountingEntryMapper.ToDTO(savedEntry);
    }
    public AccountingentryDTO getAccountingEntryById(int id) {
        return null;
    }
}