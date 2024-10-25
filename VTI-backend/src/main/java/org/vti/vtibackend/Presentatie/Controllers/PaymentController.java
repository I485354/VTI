package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Service.PaymentService;
import org.vti.vtibackend.model.Payments;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payments> getAllPayments() {
        return paymentService.GetALlPayments();
    }
    @PostMapping
    public Payments createPayments(@RequestBody Payments payment) {
        return paymentService.createPayment(payment);
    }
}