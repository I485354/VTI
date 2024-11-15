package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Mapper.InvoiceItemMapper;
import org.vti.vtibackend.BLL.Service.InvoiceItemService;
import org.vti.vtibackend.DAL.Entity.InvoiceItem;
import org.vti.vtibackend.DAL.Interface.IInvoiceItemDAL;
import org.vti.vtibackend.model.InvoiceitemDTO;

import java.util.List;
import java.util.Arrays;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class InvoiceItemServiceTest {


    @Mock
    private IInvoiceItemDAL invoiceItemDAL;

    @Mock
    private InvoiceItemMapper invoiceItemMapper;

    @InjectMocks
    private InvoiceItemService invoiceItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItems() {
        // Arrange: Mock the entity and DTO lists
        InvoiceItem item1 = new InvoiceItem(1, 101, 2, 2, 50.0, 21.0, 121.0);
        InvoiceItem item2 = new InvoiceItem(2, 101, 1, 1, 100.0, 21.0, 121.0);

        when(invoiceItemDAL.findAll()).thenReturn(Arrays.asList(item1, item2));

        InvoiceitemDTO dto1 = new InvoiceitemDTO(1, 101, 2, 2, 50.0, 21.0, 121.0);
        InvoiceitemDTO dto2 = new InvoiceitemDTO(2, 101, 1, 1, 100.0, 21.0, 121.0);

        when(invoiceItemMapper.ToDTO(item1)).thenReturn(dto1);
        when(invoiceItemMapper.ToDTO(item2)).thenReturn(dto2);

        // Act
        List<InvoiceitemDTO> items = invoiceItemService.getAllItems();

        // Assert
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getQuantity()).isEqualTo(2);
        assertThat(items.get(1).getUnit_price()).isEqualTo(100.0);

        verify(invoiceItemDAL, times(1)).findAll();
    }

    @Test
    void testCreateItems() {
        // Arrange: Mock DTO and entity for creating items
        InvoiceitemDTO itemDTO = new InvoiceitemDTO(1, 101, 2, 2, 50.0, 21.0, 121.0); // Adjust this constructor to match your DTO structure
        InvoiceItem itemEntity = new InvoiceItem(1, 101, 2, 2, 50.0, 21.0, 121.0); // Adjust this constructor to match your Entity structure

        when(invoiceItemMapper.ToEntity(itemDTO)).thenReturn(itemEntity);
        when(invoiceItemDAL.save(itemEntity)).thenReturn(itemEntity);
        when(invoiceItemMapper.ToDTO(itemEntity)).thenReturn(itemDTO);

        // Act
        InvoiceitemDTO createdItem = invoiceItemService.createItems(itemDTO);

        // Assert
        assertThat(createdItem.getQuantity()).isEqualTo(2);
        assertThat(createdItem.getUnit_price()).isEqualTo(50.0);
        assertThat(createdItem.getBtw()).isEqualTo(21.0);
        assertThat(createdItem.getTotal()).isEqualTo(121.0);

        verify(invoiceItemDAL, times(1)).save(itemEntity);
    }
}
