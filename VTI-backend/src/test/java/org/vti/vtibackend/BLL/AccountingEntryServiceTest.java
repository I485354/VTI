package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Service.AccountingEntryService;

import org.vti.vtibackend.BLL.Interface.IAccountingEntryDAL;
import org.vti.vtibackend.model.Accounting.AccountingentryDTO;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountingEntryServiceTest {

    @Mock
    private IAccountingEntryDAL accountingEntryDAL;

    @InjectMocks
    private AccountingEntryService accountingEntryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEntries() {
        // Arrange
        AccountingentryDTO entry1 = new AccountingentryDTO(1, 1, new java.util.Date(), 100.0, 0.0, "Description 1");
        AccountingentryDTO entry2 = new AccountingentryDTO(2, 2, new java.util.Date(), 200.0, 50.0, "Description 2");

        when(accountingEntryDAL.findAll()).thenReturn(Arrays.asList(entry1, entry2));

        // Act
        List<AccountingentryDTO> entries = accountingEntryService.getAllEntries();

        // Assert
        assertThat(entries).hasSize(2);
        assertThat(entries.get(0).getDescriptions()).isEqualTo("Description 1");
        assertThat(entries.get(1).getDebit_amount()).isEqualTo(200.0);

        verify(accountingEntryDAL, times(1)).findAll();
    }

    @Test
    void testCreateNewEntries() {
        // Arrange
        AccountingentryDTO newEntry = new AccountingentryDTO(3, 3, new java.util.Date(), 300.0, 100.0, "New Description");

        when(accountingEntryDAL.save(newEntry)).thenReturn(newEntry);

        // Act
        AccountingentryDTO createdEntry = accountingEntryService.createNewEntries(newEntry);

        // Assert
        assertThat(createdEntry).isNotNull();
        assertThat(createdEntry.getDescriptions()).isEqualTo("New Description");
        assertThat(createdEntry.getDebit_amount()).isEqualTo(300.0);

        verify(accountingEntryDAL, times(1)).save(newEntry);
    }

    @Test
    void testGetAccountingEntryById() {
        // Arrange
        int entryId = 1;
        AccountingentryDTO entry = new AccountingentryDTO(entryId, 1, new java.util.Date(), 150.0, 50.0, "Entry Description");

        when(accountingEntryDAL.getAccountingEntry(entryId)).thenReturn(entry);

        // Act
        AccountingentryDTO retrievedEntry = accountingEntryService.getAccountingEntryById(entryId);

        // Assert
        assertThat(retrievedEntry).isNotNull();
        assertThat(retrievedEntry.getEntry_id()).isEqualTo(entryId);
        assertThat(retrievedEntry.getDescriptions()).isEqualTo("Entry Description");

        verify(accountingEntryDAL, times(1)).getAccountingEntry(entryId);
    }
}
