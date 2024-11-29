package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.DAL.Mapper.InvoiceMapper;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.BLL.Interface.IInvoiceDAL;
import org.vti.vtibackend.model.InvoiceDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class InvoiceServiceTest {
    @Mock
    private IInvoiceDAL invoiceDAL;

    @Mock
    private InvoiceMapper invoiceMapper;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInvoices() {
        // Arrange
        InvoiceDTO dto1 = new InvoiceDTO(1L, 1L, 1, new Date(), new Date(), 100.0, 21.0, "Paid", 1001, new Date(), 500.0);
        InvoiceDTO dto2 = new InvoiceDTO(2L, 2L, 2, new Date(), new Date(), 200.0, 42.0, "Unpaid", 1002, new Date(), 200.0);

        when(invoiceDAL.findAll()).thenReturn(Arrays.asList(dto1, dto2));

        // Act
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();

        // Assert
        assertThat(invoices).hasSize(2);
        assertThat(invoices.get(0).getStatus()).isEqualTo("Paid");
        assertThat(invoices.get(1).getTotal_amount()).isEqualTo(200.0);

        verify(invoiceDAL, times(1)).findAll();
    }

    @Test
    void testCreateInvoice() {
        // Arrange
        InvoiceDTO dto = new InvoiceDTO(1L, 1L, 1, new Date(), new Date(), 150.0, 31.5, "Pending", 1001, new Date(), 500.0);

        when(invoiceDAL.save(dto)).thenReturn(dto);

        // Act
        InvoiceDTO createdInvoice = invoiceService.createInvoice(dto);

        // Assert
        assertThat(createdInvoice).isNotNull();
        assertThat(createdInvoice.getInvoice_id()).isEqualTo(1L);
        assertThat(createdInvoice.getStatus()).isEqualTo("Pending");

        verify(invoiceDAL, times(1)).save(dto);
    }
    @Test
    void testUpdateStatus() {
        // Arrange
        Long invoiceId = 1L;
        String newStatus = "Paid";


        InvoiceDTO updatedInvoiceDTO = new InvoiceDTO(1L, 1L, 1, new Date(), new Date(), 300.0, 63.0, newStatus, 1001, new Date(), 500.0);

        when(invoiceDAL.findById(invoiceId)).thenReturn(Optional.of(updatedInvoiceDTO));
        when(invoiceDAL.save(updatedInvoiceDTO)).thenReturn(updatedInvoiceDTO);

        // Act
        InvoiceDTO updatedInvoice = invoiceService.updateStatus(invoiceId, newStatus);

        // Assert
        assertThat(updatedInvoice).isNotNull();
        assertThat(updatedInvoice.getStatus()).isEqualTo(newStatus);

        verify(invoiceDAL, times(1)).findById(invoiceId);
        verify(invoiceDAL, times(1)).save(updatedInvoiceDTO);

    }
}
