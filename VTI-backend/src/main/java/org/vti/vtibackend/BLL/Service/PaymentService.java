package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.IPaymentService;
import org.vti.vtibackend.BLL.Mapper.PaymentMapper;
import org.vti.vtibackend.DAL.Entity.Payment;
import org.vti.vtibackend.DAL.Interface.IPaymentDAL;
import org.vti.vtibackend.model.PaymentDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService {

    private final IPaymentDAL paymentDAL;
    private final PaymentMapper paymentMapper;
    @Autowired
    public PaymentService(PaymentMapper paymentMapper, IPaymentDAL paymentDAL) {
        this.paymentMapper = paymentMapper;
        this.paymentDAL = paymentDAL;
    }

    public List<PaymentDTO> GetALlPayments() {
        List<Payment> payments = paymentDAL.findAll();
        return payments.stream()
                .map(paymentMapper::ToDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO createPayment(PaymentDTO payment) {
        Payment payments = paymentMapper.ToEntity(payment);
        Payment savedPayment = paymentDAL.save(payments);
        return paymentMapper.ToDTO(savedPayment);
    }
}
