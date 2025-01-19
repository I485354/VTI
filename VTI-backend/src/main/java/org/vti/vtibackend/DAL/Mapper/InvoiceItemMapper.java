package org.vti.vtibackend.DAL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.InvoiceItem;
import org.vti.vtibackend.model.InvoiceItem.InvoiceitemDTO;

@Mapper(componentModel = "spring")
public interface InvoiceItemMapper {
    @Mapping(source = "invoice_item_id", target = "invoice_item_id")
    InvoiceitemDTO ToDTO(InvoiceItem item);

    @Mapping(source = "invoice_item_id", target = "invoice_item_id")
    InvoiceItem ToEntity(InvoiceitemDTO itemDTO);
}
