package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Service.PaymentService;
import org.vti.vtibackend.model.PaymentDTO;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        return paymentService.GetALlPayments();
    }
    @PostMapping
    public PaymentDTO createPayments(@RequestBody PaymentDTO payment) {
        return paymentService.createPayment(payment);
    }
}