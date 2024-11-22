package org.vti.vtibackend.BLL;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Mapper.PaymentMapper;
import org.vti.vtibackend.BLL.Service.PaymentService;
import org.vti.vtibackend.DAL.Entity.Payment;
import org.vti.vtibackend.DAL.Interface.IPaymentDAL;
import org.vti.vtibackend.model.PaymentDTO;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {
    @Mock
    private IPaymentDAL paymentDAL;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPayments(){
        //give
        Payment paymentEntity = new Payment(1L, 1, new Date(), 100.0,"Cash");
        Payment paymentEntity2 = new Payment(2L, 2, new Date(), 100.0,"Cash");
        Payment paymentEntity3 = new Payment(3L, 3, new Date(), 100.0,"Cash");

        when(paymentDAL.findAll()).thenReturn(Arrays.asList(paymentEntity, paymentEntity2, paymentEntity3));

        PaymentDTO paymentDTO = new PaymentDTO(1L,1, new Date(), 100.0,"Cash");
        PaymentDTO paymentDTO2 = new PaymentDTO(2L,2, new Date(), 100.0,"Cash");

        when(paymentMapper.ToDTO(paymentEntity)).thenReturn(paymentDTO);
        when(paymentMapper.ToDTO(paymentEntity2)).thenReturn(paymentDTO2);

        //when
        List<PaymentDTO> payments = paymentService.GetALlPayments();

        // then
        assertThat(payments).hasSize(3);
        assertThat(payments.get(0)).isEqualTo(paymentDTO);
        assertThat(payments.get(0).getPayment_id()).isEqualTo(1);
        assertThat(payments.get(0).getPayment_date()).isEqualTo(paymentDTO.getPayment_date());

        verify(paymentDAL, times(1)).findAll();
    }

    @Test
    void CreatePayment(){
        Payment paymentEntity = new Payment(1L, 1, new Date(), 100.0,"Cash");
        Payment paymentEntity2 = new Payment(2L, 2, new Date(), 100.0,"Cash");

        PaymentDTO paymentDTO = new PaymentDTO(1L,1, new Date(), 100.0,"Cash");
        PaymentDTO paymentDTO2 = new PaymentDTO(2L,2, new Date(), 100.0,"Cash");

        when(paymentMapper.ToEntity(paymentDTO)).thenReturn(paymentEntity);
        when(paymentDAL.save(paymentEntity)).thenReturn(paymentEntity);
        when(paymentMapper.ToDTO(paymentEntity)).thenReturn(paymentDTO);

        when(paymentMapper.ToEntity(paymentDTO2)).thenReturn(paymentEntity2);
        when(paymentDAL.save(paymentEntity2)).thenReturn(paymentEntity2);
        when(paymentMapper.ToDTO(paymentEntity2)).thenReturn(paymentDTO2);

        PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);
        PaymentDTO created2Payment = paymentService.createPayment(paymentDTO2);


        assertThat(createdPayment.getPayment_id()).isEqualTo(1);
        assertThat(created2Payment.getPayment_id()).isEqualTo(2);
        assertThat(createdPayment.getAmount()).isEqualTo(100.0);
        assertThat(createdPayment.getPayment_method()).isEqualTo("Cash");
        assertThat(createdPayment.getPayment_date()).isEqualTo(paymentDTO.getPayment_date());


        verify(paymentDAL, times(1)).save(paymentEntity);
        verify(paymentMapper, times(1)).ToEntity(paymentDTO2);


    }
}