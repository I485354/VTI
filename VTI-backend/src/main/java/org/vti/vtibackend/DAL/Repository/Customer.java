package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.model.Customers;

public interface Customer extends JpaRepository<Customers, Integer> {
}
