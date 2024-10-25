package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.model.Accountingentries;
import org.vti.vtibackend.DAL.Repository.AccountingEntry;


import java.util.List;

@Service
public class AccountingEntryService {
    @Autowired
    private AccountingEntry accountingEntryRepository;

    public List<Accountingentries>
    getAllEntries() {
        return accountingEntryRepository.findAll();
    }
    public Accountingentries createNewEntries(Accountingentries entries) {
        return accountingEntryRepository.save(entries);  // Gebruik de instantie, niet de klasse
    }
}