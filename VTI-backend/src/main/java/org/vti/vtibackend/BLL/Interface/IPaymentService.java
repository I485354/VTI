package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.PaymentDTO;

import java.util.List;

public interface IPaymentService {
    List<PaymentDTO> GetALlPayments();
    PaymentDTO createPayment(PaymentDTO payment);
}
