package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Mapper.CustomerMapper;
import org.vti.vtibackend.BLL.Service.CustomerService;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.model.CustomerDTO;
import org.vti.vtibackend.DAL.Interface.ICustomerDAL;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    @Mock
    private ICustomerDAL customerDAL;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        Customer customer1 = new Customer(1, "John Doe", "john@example.com", "Doe Enterprises", "123-456", "123 Main St");
        Customer customer2 = new Customer(2, "Jane Smith", "jane@example.com", "Smith Solutions", "234-567", "456 Oak Ave");

        when(customerDAL.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        when(customerMapper.ToDTO(customer1)).thenReturn(new CustomerDTO(1, "John Doe", "john@example.com", "Doe Enterprises", "123-456", "123 Main St"));
        when(customerMapper.ToDTO(customer2)).thenReturn(new CustomerDTO(2, "Jane Smith", "jane@example.com", "Smith Solutions", "234-567", "456 Oak Ave"));

        // Act
        List<CustomerDTO> customers = customerService.getAllCustomers();

        // Assert
        assertThat(customers).hasSize(2);
        assertThat(customers.get(0).getName()).isEqualTo("John Doe");
        assertThat(customers.get(1).getName()).isEqualTo("Jane Smith");

        verify(customerDAL, times(1)).findAll();
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO(1, "John Doe", "john@example.com", "Doe Enterprises", "123-456", "123 Main St");
        Customer customerEntity = new Customer(1, "John Doe", "john@example.com", "Doe Enterprises", "123-456", "123 Main St");

        when(customerMapper.ToEntity(customerDTO)).thenReturn(customerEntity);
        when(customerDAL.save(customerEntity)).thenReturn(customerEntity);
        when(customerMapper.ToDTO(customerEntity)).thenReturn(customerDTO);

        // Act
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);

        // Assert
        assertThat(createdCustomer.getName()).isEqualTo("John Doe");
        verify(customerDAL, times(1)).save(customerEntity);
    }

    @Test
    void testUpdateCustomer() {
        // Arrange
        int customerId = 1;
        CustomerDTO customerDTO = new CustomerDTO(customerId, "John Updated", "john_updated@example.com", "Doe Updated Enterprises", "123-789", "789 Pine St");
        Customer existingCustomer = new Customer(customerId, "John Doe", "john@example.com", "Doe Enterprises", "123-456", "123 Main St");

        when(customerDAL.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerDAL.save(existingCustomer)).thenReturn(existingCustomer);
        when(customerMapper.ToDTO(existingCustomer)).thenReturn(customerDTO);

        // Act
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customerDTO);

        // Assert
        assertThat(updatedCustomer.getName()).isEqualTo("John Updated");
        assertThat(updatedCustomer.getEmail()).isEqualTo("john_updated@example.com");
        verify(customerDAL, times(1)).findById(customerId);
        verify(customerDAL, times(1)).save(existingCustomer);
    }

    @Test
    void testUpdateCustomerNotFound() {
        // Arrange
        int customerId = 1;
        CustomerDTO customerDTO = new CustomerDTO(customerId, "John Updated", "john_updated@example.com", "Doe Updated Enterprises", "123-789", "789 Pine St");

        when(customerDAL.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> customerService.updateCustomer(customerId, customerDTO));
        verify(customerDAL, times(1)).findById(customerId);
        verify(customerDAL, never()).save(any());
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        int customerId = 1;

        doNothing().when(customerDAL).deleteById(customerId);

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerDAL, times(1)).deleteById(customerId);
    }
}
