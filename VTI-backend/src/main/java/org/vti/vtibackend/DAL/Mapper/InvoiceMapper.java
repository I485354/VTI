package org.vti.vtibackend.DAL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.model.InvoiceDTO;


@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(source = "invoice_id", target = "invoice_id")
    InvoiceDTO toDTO(Invoice invoice);

    @Mapping(source = "invoice_id", target = "invoice_id")
    Invoice toEntity(InvoiceDTO invoiceDTO);
}