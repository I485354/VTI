package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Payment;
import org.vti.vtibackend.DAL.Interface.IPaymentDAL;
import org.vti.vtibackend.DAL.Repository.PaymentRepo;

import java.util.List;

@Repository
public class PaymentDAL implements IPaymentDAL {

    private final PaymentRepo paymentRepo;

    @Autowired
    public PaymentDAL(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepo.findAll();
    }
}
