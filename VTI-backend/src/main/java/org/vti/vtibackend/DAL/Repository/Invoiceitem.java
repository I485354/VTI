package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.model.Invoiceitems;

public interface Invoiceitem extends JpaRepository<Invoiceitems, Integer> {
}