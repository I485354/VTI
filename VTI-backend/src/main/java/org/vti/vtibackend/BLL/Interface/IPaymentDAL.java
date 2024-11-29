package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.DAL.Entity.Payment;
import org.vti.vtibackend.model.PaymentDTO;

import java.util.List;

public interface IPaymentDAL {
    PaymentDTO save(PaymentDTO paymentDTO);
    List<PaymentDTO> findAll();

}
