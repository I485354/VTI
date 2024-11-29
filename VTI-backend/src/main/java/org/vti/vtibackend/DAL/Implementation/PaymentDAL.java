package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Payment;
import org.vti.vtibackend.BLL.Interface.IPaymentDAL;
import org.vti.vtibackend.DAL.Mapper.PaymentMapper;
import org.vti.vtibackend.DAL.Repository.PaymentRepo;
import org.vti.vtibackend.model.PaymentDTO;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PaymentDAL implements IPaymentDAL {

    private final PaymentRepo paymentRepo;
    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentDAL(PaymentRepo paymentRepo, PaymentMapper paymentMapper) {
        this.paymentRepo = paymentRepo;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDTO save(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.ToEntity(paymentDTO);
        Payment saved = paymentRepo.save(payment);
        return paymentMapper.ToDTO(saved);
    }

    @Override
    public List<PaymentDTO> findAll() {
        return paymentRepo.findAll()
                .stream()
                .map(paymentMapper::ToDTO)
                .collect(Collectors.toList());
    }
}
