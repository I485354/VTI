package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.IPaymentDAL;
import org.vti.vtibackend.model.Payment.PaymentDTO;

import java.util.List;

@Service
public class PaymentService {

    private final IPaymentDAL paymentDAL;

    @Autowired
    public PaymentService( IPaymentDAL paymentDAL) {

        this.paymentDAL = paymentDAL;
    }

    public List<PaymentDTO> GetALlPayments() {
        return paymentDAL.findAll();
    }

    public PaymentDTO createPayment(PaymentDTO payment) {
        if (payment == null) {
            throw new NullPointerException("Payment cannot be null");
        }
        if (payment.getAmount() < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative");
        }
        return paymentDAL.save(payment);
    }
}
