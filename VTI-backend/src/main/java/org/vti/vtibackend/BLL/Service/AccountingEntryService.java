package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Mapper.AccountingEntryMapper;
import org.vti.vtibackend.DAL.Entity.AccountingEntry;
import org.vti.vtibackend.model.AccountingentryDTO;
import org.vti.vtibackend.DAL.Repository.AccountingEntryRepo;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountingEntryService {

    private final AccountingEntryRepo accountingEntryRepository;
    private final AccountingEntryMapper accountingEntryMapper;
    @Autowired
    public AccountingEntryService(AccountingEntryRepo accountingEntryRepository,AccountingEntryMapper accountingEntryMapper) {
        this.accountingEntryRepository = accountingEntryRepository;
        this.accountingEntryMapper = accountingEntryMapper;
    }

    public List<AccountingentryDTO>
    getAllEntries() {
        List<AccountingEntry> entries = accountingEntryRepository.findAll();
        return entries.stream()
                .map(accountingEntryMapper::ToDTO)
                .collect(Collectors.toList());
    }
    public AccountingentryDTO createNewEntries(AccountingentryDTO entries) {
        AccountingEntry entry = accountingEntryMapper.ToEntity(entries);
        AccountingEntry savedEntry = accountingEntryRepository.save(entry);
        return accountingEntryMapper.ToDTO(savedEntry);
    }
}