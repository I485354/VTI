package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Mapper.AccountingEntryMapper;
import org.vti.vtibackend.BLL.Service.AccountingEntryService;
import org.vti.vtibackend.DAL.Entity.AccountingEntry;
import org.vti.vtibackend.DAL.Interface.IAccountingEntryDAL;
import org.vti.vtibackend.model.AccountingentryDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

public class AccountingEntryServiceTest {


    @Mock
    private IAccountingEntryDAL accountingEntryDAL;

    @Mock
    private AccountingEntryMapper accountingEntryMapper;

    @InjectMocks
    private AccountingEntryService accountingEntryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEntries() {
        // Arrange
        Date date = new Date();
        AccountingEntry entry1 = new AccountingEntry(1L, 101, date, 500.0, 0.0, "Invoice payment");
        AccountingEntry entry2 = new AccountingEntry(2L, 102, date, 0.0, 300.0, "Refund");

        when(accountingEntryDAL.findAll()).thenReturn(Arrays.asList(entry1, entry2));

        AccountingentryDTO dto1 = new AccountingentryDTO(1L, 101, date, 500.0, 0.0, "Invoice payment");
        AccountingentryDTO dto2 = new AccountingentryDTO(2L, 102, date, 0.0, 300.0, "Refund");

        when(accountingEntryMapper.ToDTO(entry1)).thenReturn(dto1);
        when(accountingEntryMapper.ToDTO(entry2)).thenReturn(dto2);

        // Act
        List<AccountingentryDTO> entries = accountingEntryService.getAllEntries();

        // Assert
        assertThat(entries).hasSize(2);
        assertThat(entries.get(0).getDescriptions()).isEqualTo("Invoice payment");
        assertThat(entries.get(1).getDescriptions()).isEqualTo("Refund");
        assertThat(entries.get(0).getDebit_amount()).isEqualTo(500.0);
        assertThat(entries.get(1).getCredit_amount()).isEqualTo(300.0);

        verify(accountingEntryDAL, times(1)).findAll();
    }

    @Test
    void testCreateNewEntries() {
        // Arrange
        Date date = new Date();
        AccountingentryDTO entryDTO = new AccountingentryDTO(1L, 101, date, 500.0, 0.0, "Invoice payment");
        AccountingEntry entryEntity = new AccountingEntry(1L, 101, date, 500.0, 0.0, "Invoice payment");

        when(accountingEntryMapper.ToEntity(entryDTO)).thenReturn(entryEntity);
        when(accountingEntryDAL.save(entryEntity)).thenReturn(entryEntity);
        when(accountingEntryMapper.ToDTO(entryEntity)).thenReturn(entryDTO);

        // Act
        AccountingentryDTO createdEntry = accountingEntryService.createNewEntries(entryDTO);

        // Assert
        assertThat(createdEntry.getDescriptions()).isEqualTo("Invoice payment");
        assertThat(createdEntry.getDebit_amount()).isEqualTo(500.0);
        assertThat(createdEntry.getInvoice_id()).isEqualTo(101);
        assertThat(createdEntry.getEntry_date()).isEqualTo(date);

        verify(accountingEntryDAL, times(1)).save(entryEntity);
    }
}
