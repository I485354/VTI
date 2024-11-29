package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.DAL.Mapper.InvoiceItemMapper;
import org.vti.vtibackend.BLL.Service.InvoiceItemService;
import org.vti.vtibackend.DAL.Entity.InvoiceItem;
import org.vti.vtibackend.BLL.Interface.IInvoiceItemDAL;
import org.vti.vtibackend.model.InvoiceitemDTO;

import java.util.List;
import java.util.Arrays;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class InvoiceItemServiceTest {


    @Mock
    private IInvoiceItemDAL invoiceItemDAL;

    @InjectMocks
    private InvoiceItemService invoiceItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItems() {
        // Arrange
        InvoiceitemDTO item1 = new InvoiceitemDTO(1, 1, 1, 2, 50.0, 21, 121.0);
        InvoiceitemDTO item2 = new InvoiceitemDTO(2, 1, 2, 1, 30.0, 9, 32.7);

        when(invoiceItemDAL.findAll()).thenReturn(Arrays.asList(item1, item2));

        // Act
        List<InvoiceitemDTO> items = invoiceItemService.getAllItems();

        // Assert
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getProduct_id()).isEqualTo(1);
        assertThat(items.get(1).getTotal()).isEqualTo(32.7);

        verify(invoiceItemDAL, times(1)).findAll();
    }

    @Test
    void testCreateItems() {
        // Arrange
        InvoiceitemDTO newItem = new InvoiceitemDTO(1, 1, 1, 2, 50.0, 21, 121.0);

        when(invoiceItemDAL.save(newItem)).thenReturn(newItem);

        // Act
        InvoiceitemDTO createdItem = invoiceItemService.createItems(newItem);

        // Assert
        assertThat(createdItem).isNotNull();
        assertThat(createdItem.getQuantity()).isEqualTo(2);
        assertThat(createdItem.getUnit_price()).isEqualTo(50.0);

        verify(invoiceItemDAL, times(1)).save(newItem);
    }
}
