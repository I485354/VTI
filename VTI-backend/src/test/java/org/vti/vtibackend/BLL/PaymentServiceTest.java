package org.vti.vtibackend.BLL;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.DAL.Mapper.PaymentMapper;
import org.vti.vtibackend.BLL.Service.PaymentService;
import org.vti.vtibackend.DAL.Entity.Payment;
import org.vti.vtibackend.BLL.Interface.IPaymentDAL;
import org.vti.vtibackend.model.PaymentDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {
    @Mock
    private IPaymentDAL paymentDAL;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPayments() {
        // Arrange
        PaymentDTO payment1 = new PaymentDTO(1L, 1, new java.util.Date(), 100.0, "Cash");
        PaymentDTO payment2 = new PaymentDTO(2L, 2, new java.util.Date(), 200.0, "Credit");
        when(paymentDAL.findAll()).thenReturn(Arrays.asList(payment1, payment2));

        List<PaymentDTO> payments = paymentService.GetALlPayments();

        assertThat(payments).hasSize(2);
        assertThat(payments.get(0).getPayment_id()).isEqualTo(1L);
        assertThat(payments.get(1).getAmount()).isEqualTo(200.0);

        verify(paymentDAL, times(1)).findAll();
    }

    @Test
    void testGetAllPayments_EmptyList() {
        when(paymentDAL.findAll()).thenReturn(Collections.emptyList());

        List<PaymentDTO> payments = paymentService.GetALlPayments();

        assertThat(payments).isEmpty();
        verify(paymentDAL, times(1)).findAll();
    }

    @Test
    void testCreatePayment() {
        PaymentDTO newPayment = new PaymentDTO(1L, 1, new java.util.Date(), 150.0, "Bank Transfer");
        when(paymentDAL.save(newPayment)).thenReturn(newPayment);

        PaymentDTO createdPayment = paymentService.createPayment(newPayment);

        assertThat(createdPayment).isNotNull();
        assertThat(createdPayment.getAmount()).isEqualTo(150.0);
        assertThat(createdPayment.getPayment_method()).isEqualTo("Bank Transfer");

        verify(paymentDAL, times(1)).save(newPayment);
    }

    @Test
    void testCreatePayment_NegativeAmount() {
        PaymentDTO invalidPayment = new PaymentDTO(1L, 1, new java.util.Date(), -50.0, "Cash");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.createPayment(invalidPayment);
        });

        assertThat(exception.getMessage()).isEqualTo("Payment amount cannot be negative");
        verify(paymentDAL, never()).save(any());
    }

    @Test
    void testCreatePayment_NullPayment() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            paymentService.createPayment(null);
        });

        assertThat(exception.getMessage()).isEqualTo("Payment cannot be null");
        verify(paymentDAL, never()).save(any());
    }

    @Test
    void testGetAllPayments_DatabaseError() {
        when(paymentDAL.findAll()).thenThrow(new RuntimeException("Database connection error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            paymentService.GetALlPayments();
        });
        assertThat(exception.getMessage()).isEqualTo("Database connection error");
        verify(paymentDAL, times(1)).findAll();
    }
}