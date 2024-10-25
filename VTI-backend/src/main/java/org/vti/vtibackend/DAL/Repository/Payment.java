package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.model.Payments;

public interface Payment extends JpaRepository<Payments, Integer> {
}
