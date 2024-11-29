package org.vti.vtibackend.DAL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.AccountingEntry;
import org.vti.vtibackend.model.AccountingentryDTO;

@Mapper(componentModel = "spring")
public interface AccountingEntryMapper {
    @Mapping(source = "entry_id", target = "entry_id")
    AccountingentryDTO ToDTO(AccountingEntry accountingEntry);

    @Mapping(source = "entry_id", target = "entry_id")
    AccountingEntry ToEntity(AccountingentryDTO accountingentryDTO);
}
