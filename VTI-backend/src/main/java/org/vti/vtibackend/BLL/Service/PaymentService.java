package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.DAL.Repository.Payment;
import org.vti.vtibackend.model.Payments;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private Payment paymentRepository;

    public List<Payments> GetALlPayments() {
        return paymentRepository.findAll();
    }
    public Payments createPayment(Payments payment) {
        return paymentRepository.save(payment);
    }
}
