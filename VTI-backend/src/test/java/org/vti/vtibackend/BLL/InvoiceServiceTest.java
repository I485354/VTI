package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.BLL.Interface.IInvoiceDAL;
import org.vti.vtibackend.model.Invoice.CreateInvoiceDTO;
import org.vti.vtibackend.model.Invoice.InvoiceDTO;
import org.vti.vtibackend.model.Invoice.UpdateInvoiceStatusDTO;


import java.util.Date;
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
}
