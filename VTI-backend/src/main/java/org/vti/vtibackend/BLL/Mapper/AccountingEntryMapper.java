package org.vti.vtibackend.BLL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.AccountingEntry;
import org.vti.vtibackend.model.AccountingentryDTO;

@Mapper(componentModel = "spring")
public interface AccountingEntryMapper {
    @Mapping(source = "entry_id", target = "entry_id")
    @Mapping(source = "entry_date", target = "entry_date")
    AccountingentryDTO ToDTO(AccountingEntry accountingEntry);

    @Mapping(source = "entry_id", target = "entry_id")
    @Mapping(source = "entry_date", target = "entry_date")
    AccountingEntry ToEntity(AccountingentryDTO accountingentryDTO);
}
