package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.DAL.Entity.Payment;


public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
