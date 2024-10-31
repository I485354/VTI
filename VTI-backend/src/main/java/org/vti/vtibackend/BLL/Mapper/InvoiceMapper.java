package org.vti.vtibackend.BLL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.model.InvoiceDTO;


@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(source = "invoice_id", target = "invoice_id")
    @Mapping(source = "customer_id", target = "customer_id") // Als customerId uit een embedded customer-object komt
    InvoiceDTO toDTO(Invoice invoice);

    @Mapping(source = "invoice_id", target = "invoice_id")
    @Mapping(source = "customer_id", target = "customer_id")
    Invoice toEntity(InvoiceDTO invoiceDTO);
}