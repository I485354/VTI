package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.BLL.Interface.IInvoiceDAL;
import org.vti.vtibackend.model.Invoice.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvoiceServiceTest {

    @Mock
    private IInvoiceDAL invoiceDAL;

    @InjectMocks
    private InvoiceService invoiceService;

    private CreateInvoiceDTO validCreateInvoiceDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        Date newDate = new Date();
        validCreateInvoiceDTO = new CreateInvoiceDTO();
        validCreateInvoiceDTO.setCustomer_id(1);
        validCreateInvoiceDTO.setCar_id(1);
        validCreateInvoiceDTO.setInvoice_date(newDate);
        validCreateInvoiceDTO.setDue_date(newDate);
        validCreateInvoiceDTO.setTotal_amount(1000.00);
        validCreateInvoiceDTO.setTotal_btw(210.00);
        validCreateInvoiceDTO.setStatus("Pending");
    }

    @Test
    void shouldCreateInvoiceSuccessfully() {
        // Arrange
        when(invoiceDAL.findHighestInvoiceNumber()).thenReturn(100);
        InvoiceDTO expectedInvoice = new InvoiceDTO();
        expectedInvoice.setCustomer_id(validCreateInvoiceDTO.getCustomer_id());
        expectedInvoice.setCar_id(validCreateInvoiceDTO.getCar_id());
        expectedInvoice.setInvoice_date(validCreateInvoiceDTO.getInvoice_date());
        expectedInvoice.setDue_date(validCreateInvoiceDTO.getDue_date());
        expectedInvoice.setTotal_amount(validCreateInvoiceDTO.getTotal_amount());
        expectedInvoice.setTotal_btw(validCreateInvoiceDTO.getTotal_btw());
        expectedInvoice.setStatus(validCreateInvoiceDTO.getStatus());
        expectedInvoice.setInvoice_number(101);

        when(invoiceDAL.save(any(InvoiceDTO.class))).thenReturn(expectedInvoice);

        // Act
        InvoiceDTO result = invoiceService.createInvoice(validCreateInvoiceDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedInvoice.getInvoice_number(), result.getInvoice_number());
        assertEquals(expectedInvoice.getCustomer_id(), result.getCustomer_id());
    }

    @Test
    void shouldThrowExceptionWhenDueDateIsNull() {
        validCreateInvoiceDTO.setDue_date(null);
        assertThrows(IllegalArgumentException.class,
                () -> invoiceService.createInvoice(validCreateInvoiceDTO),
                "Due date is required.");
    }

    @Test
    void shouldThrowExceptionWhenCustomerIdIsZero() {
        validCreateInvoiceDTO.setCustomer_id(0);
        assertThrows(IllegalArgumentException.class,
                () -> invoiceService.createInvoice(validCreateInvoiceDTO),
                "Customer ID must be greater than 0.");
    }

    @Test
    void shouldThrowExceptionWhenTotalAmountIsZeroOrLess() {
        validCreateInvoiceDTO.setTotal_amount(0);
        assertThrows(IllegalArgumentException.class,
                () -> invoiceService.createInvoice(validCreateInvoiceDTO),
                "Total amount must be greater than 0.");
    }

    @Test
    void shouldThrowExceptionWhenTotalBtwIsZeroOrLess() {
        validCreateInvoiceDTO.setTotal_btw(0);
        assertThrows(IllegalArgumentException.class,
                () -> invoiceService.createInvoice(validCreateInvoiceDTO),
                "Total BTW must be greater than 0.");
    }

    @Test
    void shouldHandleExceptionFromHighestInvoiceNumber() {

        when(invoiceDAL.findHighestInvoiceNumber()).thenThrow(new RuntimeException("DB error"));

        InvoiceDTO fallbackInvoice = new InvoiceDTO();
        fallbackInvoice.setInvoice_number(1);
        when(invoiceDAL.save(any(InvoiceDTO.class))).thenReturn(fallbackInvoice);


        InvoiceDTO result = invoiceService.createInvoice(validCreateInvoiceDTO);

        assertNotNull(result);
        assertEquals(1, result.getInvoice_number());
        verify(invoiceDAL).findHighestInvoiceNumber(); // throws
        verify(invoiceDAL).save(any(InvoiceDTO.class));
    }

    @Test
    void shouldReturnAllInvoices() {
        // Arrange
        InvoiceDTO inv1 = new InvoiceDTO();
        inv1.setInvoice_id(1L);
        InvoiceDTO inv2 = new InvoiceDTO();
        inv2.setInvoice_id(2L);
        when(invoiceDAL.findAll()).thenReturn(List.of(inv1, inv2));

        // Act
        List<InvoiceDTO> allInvoices = invoiceService.getAllInvoices();

        // Assert
        assertEquals(2, allInvoices.size());
        assertEquals(1L, allInvoices.get(0).getInvoice_id());
        verify(invoiceDAL).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoInvoices() {
        when(invoiceDAL.findAll()).thenReturn(new ArrayList<>());

        List<InvoiceDTO> allInvoices = invoiceService.getAllInvoices();
        assertTrue(allInvoices.isEmpty());
        verify(invoiceDAL).findAll();
    }

    @Test
    void shouldThrowExceptionWhenMissingRequiredFields() {
        // Arrange
        validCreateInvoiceDTO.setInvoice_date(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            invoiceService.createInvoice(validCreateInvoiceDTO);
        });
        assertEquals("Invoice date is required.", exception.getMessage());
    }

    @Test
    void shouldUpdateStatusSuccessfully() {
        // Arrange
        UpdateInvoiceStatusDTO updateStatusDTO = new UpdateInvoiceStatusDTO();
        updateStatusDTO.setStatus("Paid");

        InvoiceDTO existingInvoice = new InvoiceDTO();
        existingInvoice.setInvoice_id(1);
        existingInvoice.setStatus("Pending");

        when(invoiceDAL.findById(1L)).thenReturn(Optional.of(existingInvoice));
        when(invoiceDAL.save(existingInvoice)).thenReturn(existingInvoice);

        // Act
        InvoiceDTO result = invoiceService.updateStatus(1L, updateStatusDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Paid", result.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenInvoiceNotFound() {
        // Arrange
        when(invoiceDAL.findById(1L)).thenReturn(Optional.empty());

        UpdateInvoiceStatusDTO updateStatusDTO = new UpdateInvoiceStatusDTO();
        updateStatusDTO.setStatus("Paid");

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            invoiceService.updateStatus(1L, updateStatusDTO);
        });
        assertEquals("Invoice not found", exception.getMessage());
    }
    @Test
    void shouldReturnOpenInvoicesCount() {
        when(invoiceDAL.countOpenInvoices()).thenReturn(5);
        int result = invoiceService.getOpenInvoicesCount();
        assertEquals(5, result);
        verify(invoiceDAL).countOpenInvoices();
    }

    @Test
    void shouldReturnZeroWhenNoOpenInvoices() {
        when(invoiceDAL.countOpenInvoices()).thenReturn(0);
        int result = invoiceService.getOpenInvoicesCount();
        assertEquals(0, result);
    }

    @Test
    void shouldReturnInvoicesByYear() {
        int year = 2025;
        InvoiceYearSummaryDTO summary1 = new InvoiceYearSummaryDTO();
        summary1.setYear(year);
        summary1.setInvoice_count(10);

        when(invoiceDAL.findInvoicesByYear(year)).thenReturn(List.of(summary1));

        List<InvoiceYearSummaryDTO> result = invoiceService.getInvoicesByYear(year);
        assertEquals(1, result.size());
        assertEquals(2025, result.get(0).getYear());
        assertEquals(10, result.get(0).getInvoice_count());
        verify(invoiceDAL).findInvoicesByYear(year);
    }

    @Test
    void shouldReturnEmptyListIfNoInvoicesForYear() {
        when(invoiceDAL.findInvoicesByYear(2025)).thenReturn(List.of());
        List<InvoiceYearSummaryDTO> result = invoiceService.getInvoicesByYear(2025);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindInvoicesWithCustomer() {
        InvoiceAndCustomerDTO dto1 = new InvoiceAndCustomerDTO();
        InvoiceAndCustomerDTO dto2 = new InvoiceAndCustomerDTO();
        when(invoiceDAL.findInvoices()).thenReturn(List.of(dto1, dto2));

        List<InvoiceAndCustomerDTO> result = invoiceService.findInvoicesWithCustomer();
        assertEquals(2, result.size());
        verify(invoiceDAL).findInvoices();
    }

    @Test
    void shouldReturnEmptyWhenNoInvoicesWithCustomer() {
        when(invoiceDAL.findInvoices()).thenReturn(List.of());

        List<InvoiceAndCustomerDTO> result = invoiceService.findInvoicesWithCustomer();
        assertTrue(result.isEmpty());
    }
}
