package org.vti.vtibackend.BLL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.Payment;
import org.vti.vtibackend.model.PaymentDTO;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "payment_id", target = "payment_id")
    PaymentDTO ToDTO(Payment payment);

    @Mapping(source = "payment_id", target = "payment_id")
    Payment ToEntity(PaymentDTO paymentDTO);
}
