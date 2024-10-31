package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.DAL.Entity.AccountingEntry;


public interface AccountingEntryRepo extends JpaRepository<AccountingEntry, Integer> {
}

