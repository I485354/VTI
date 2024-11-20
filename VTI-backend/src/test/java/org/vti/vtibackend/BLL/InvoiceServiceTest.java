package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Mapper.InvoiceMapper;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.DAL.Entity.Invoice;
import org.vti.vtibackend.DAL.Interface.IInvoiceDAL;
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
        Invoice invoice1 = new Invoice(1L, 1L,1,  new Date(), new Date(), 100.0, 21.0, "Paid", 1001);
        Invoice invoice2 = new Invoice(2L, 2L,2,  new Date(), new Date(), 200.0, 42.0, "Unpaid", 1002);

        when(invoiceDAL.findAll()).thenReturn(Arrays.asList(invoice1, invoice2));

        InvoiceDTO dto1 = new InvoiceDTO(1L, 1L,1,  new Date(), new Date(), 100.0, 21.0, "Paid",1001);
        InvoiceDTO dto2 = new InvoiceDTO(2L, 2L,2,  new Date(), new Date(), 200.0, 42.0, "Unpaid", 1002);

        when(invoiceMapper.toDTO(invoice1)).thenReturn(dto1);
        when(invoiceMapper.toDTO(invoice2)).thenReturn(dto2);

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
        InvoiceDTO invoiceDTO = new InvoiceDTO(1L, 1L,1,  new Date(), new Date(), 150.0, 31.5, "Pending", 1001);
        Invoice invoiceEntity = new Invoice(1L, 1L,1,  new Date(), new Date(), 150.0, 31.5, "Pending", 1001);

        when(invoiceMapper.toEntity(invoiceDTO)).thenReturn(invoiceEntity);
        when(invoiceDAL.save(invoiceEntity)).thenReturn(invoiceEntity);
        when(invoiceMapper.toDTO(invoiceEntity)).thenReturn(invoiceDTO);

        // Act
        InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);

        // Assert
        assertThat(createdInvoice.getTotal_amount()).isEqualTo(150.0);
        assertThat(createdInvoice.getStatus()).isEqualTo("Pending");

        verify(invoiceDAL, times(1)).save(invoiceEntity);
    }

    @Test
    void testUpdateStatus() {
        // Arrange
        Long invoiceId = 1L;
        String newStatus = "Paid";

        Invoice existingInvoice = new Invoice(invoiceId, 1L,1,  new Date(), new Date(), 300.0, 63.0, "Unpaid", 1001);
        Invoice updatedInvoice = new Invoice(invoiceId, 1L,1,  new Date(), new Date(), 300.0, 63.0, newStatus, 1001);

        when(invoiceDAL.findById(invoiceId)).thenReturn(Optional.of(existingInvoice));
        when(invoiceDAL.save(existingInvoice)).thenReturn(updatedInvoice);
        when(invoiceMapper.toDTO(updatedInvoice)).thenReturn(new InvoiceDTO(invoiceId, 1L,1,  new Date(), new Date(), 300.0, 63.0, newStatus, 1001));

        // Act
        InvoiceDTO updatedInvoiceDTO = invoiceService.updateStatus(invoiceId, newStatus);

        // Assert
        assertThat(updatedInvoiceDTO.getStatus()).isEqualTo(newStatus);

        verify(invoiceDAL, times(1)).findById(invoiceId);
        verify(invoiceDAL, times(1)).save(existingInvoice);
    }
}
