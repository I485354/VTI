package org.vti.vtibackend.DAL.Interface;

import org.vti.vtibackend.DAL.Entity.Payment;

import java.util.List;

public interface IPaymentDAL {
    Payment save(Payment payment);
    List<Payment> findAll();

}
