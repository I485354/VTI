package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.model.Accountingentries;

public interface AccountingEntry extends JpaRepository<Accountingentries, Integer> {
}

