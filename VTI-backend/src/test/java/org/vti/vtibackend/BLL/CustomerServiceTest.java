package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.DAL.Mapper.CustomerMapper;
import org.vti.vtibackend.BLL.Service.CustomerService;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.model.CustomerDTO;
import org.vti.vtibackend.BLL.Interface.ICustomerDAL;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private ICustomerDAL customerDAL;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        CustomerDTO customer1 = new CustomerDTO(1, "John Doe", "Doe Company", "john.doe@example.com", "123 Main St", "1234567890");
        CustomerDTO customer2 = new CustomerDTO(2, "Jane Smith", "Smith Motors", "jane.smith@example.com", "456 Elm St", "0987654321");

        when(customerDAL.findAll()).thenReturn(Arrays.asList(customer1, customer2));

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
        CustomerDTO newCustomer = new CustomerDTO(1, "John Doe", "Doe Company", "john.doe@example.com", "123 Main St", "1234567890");

        when(customerDAL.save(newCustomer)).thenReturn(newCustomer);

        // Act
        CustomerDTO createdCustomer = customerService.createCustomer(newCustomer);

        // Assert
        assertThat(createdCustomer).isNotNull();
        assertThat(createdCustomer.getName()).isEqualTo("John Doe");
        assertThat(createdCustomer.getEmail()).isEqualTo("john.doe@example.com");

        verify(customerDAL, times(1)).save(newCustomer);
    }

    @Test
    void testUpdateCustomer() {
        // Arrange
        int customerId = 1;
        CustomerDTO existingCustomer = new CustomerDTO(customerId, "John Doe", "Doe Company", "john.doe@example.com", "123 Main St", "1234567890");
        CustomerDTO updatedCustomer = new CustomerDTO(customerId, "John Updated", "Updated Company", "john.updated@example.com", "789 Oak St", "9876543210");

        when(customerDAL.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerDAL.save(existingCustomer)).thenReturn(updatedCustomer);

        // Act
        CustomerDTO result = customerService.updateCustomer(customerId, updatedCustomer);

        // Assert
        assertThat(result.getName()).isEqualTo("John Updated");
        assertThat(result.getCompany()).isEqualTo("Updated Company");
        assertThat(result.getEmail()).isEqualTo("john.updated@example.com");

        verify(customerDAL, times(1)).findById(customerId);
        verify(customerDAL, times(1)).save(existingCustomer);
    }

    @Test
    void testUpdateCustomerNotFound() {
        // Arrange
        int customerId = 1;
        CustomerDTO updatedCustomer = new CustomerDTO(customerId, "John Updated", "Updated Company", "john.updated@example.com", "789 Oak St", "9876543210");

        when(customerDAL.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> customerService.updateCustomer(customerId, updatedCustomer));

        verify(customerDAL, times(1)).findById(customerId);
        verify(customerDAL, times(0)).save(any());
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        int customerId = 1;

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerDAL, times(1)).deleteById(customerId);
    }
}
