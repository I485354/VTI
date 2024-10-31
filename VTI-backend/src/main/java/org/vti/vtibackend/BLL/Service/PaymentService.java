package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Mapper.PaymentMapper;
import org.vti.vtibackend.DAL.Entity.Payment;
import org.vti.vtibackend.DAL.Repository.PaymentRepo;
import org.vti.vtibackend.model.PaymentDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepo paymentRepository;
    private final PaymentMapper paymentMapper;
    @Autowired
    public PaymentService(PaymentRepo paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }
    public List<PaymentDTO> GetALlPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(paymentMapper::ToDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO createPayment(PaymentDTO payment) {
        Payment payments = paymentMapper.ToEntity(payment);
        Payment savedPayment = paymentRepository.save(payments);
        return paymentMapper.ToDTO(savedPayment);
    }
}
