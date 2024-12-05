package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.DAL.Mapper.CustomerMapper;
import org.vti.vtibackend.BLL.Service.CustomerService;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.model.CreateCustomerDTO;
import org.vti.vtibackend.model.CustomerDTO;
import org.vti.vtibackend.BLL.Interface.ICustomerDAL;
import org.vti.vtibackend.model.UpdatedCustomerDTO;

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
        CustomerDTO customer1 = new CustomerDTO(1, "John Doe", "Doe Company", "john.doe@example.com", "123 Main St", "1234567890", 2);
        CustomerDTO customer2 = new CustomerDTO(2, "Jane Smith", "Smith Motors", "jane.smith@example.com", "456 Elm St", "0987654321",3);

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
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO();
        createCustomerDTO.setName("John Doe");
        createCustomerDTO.setEmail("john.doe@example.com");
        createCustomerDTO.setPhone("1234567890");
        createCustomerDTO.setAddress("123 Main St");
        createCustomerDTO.setCompany("Doe Enterprises");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setPhone("1234567890");
        customerDTO.setAddress("123 Main St");
        customerDTO.setCompany("Doe Enterprises");
        customerDTO.setCustomer_number(1);

        // Simuleer dat het DAL de hoogste klantnummer vindt en incrementeert
        when(customerDAL.findHighestCustomerNumber()).thenReturn(0);

        // Simuleer dat de klant wordt opgeslagen
        when(customerDAL.createCustomer(any(CustomerDTO.class))).thenReturn(customerDTO);

        // Act
        CustomerDTO result = customerService.createCustomer(createCustomerDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("John Doe");
        assertThat(result.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(result.getPhone()).isEqualTo("1234567890");
        assertThat(result.getAddress()).isEqualTo("123 Main St");
        assertThat(result.getCompany()).isEqualTo("Doe Enterprises");
        assertThat(result.getCustomer_number()).isEqualTo(1);

        // Verifieer dat de juiste methodes in de DAL zijn aangeroepen
        verify(customerDAL, times(1)).findHighestCustomerNumber();
        verify(customerDAL, times(1)).createCustomer(any(CustomerDTO.class));
    }
    @Test
    void testUpdateCustomer() {
        // Arrange
        int customerId = 1;

        // Simuleer de bestaande klant (CustomerDTO die uit de DAL komt)
        CustomerDTO existingCustomerDTO = new CustomerDTO(
                customerId,
                "John Doe",
                "Doe Company",
                "123 Main St",
                "john.doe@example.com",
                "1234567890",
                2
        );

        // Nieuwe gegevens (UpdatedCustomerDTO die wordt meegegeven voor update)
        UpdatedCustomerDTO updatedCustomerDTO = new UpdatedCustomerDTO();
        updatedCustomerDTO.setName("John Updated");
        updatedCustomerDTO.setCompany("Updated Company");
        updatedCustomerDTO.setEmail("john.updated@example.com");
        updatedCustomerDTO.setPhone("9876543210");
        updatedCustomerDTO.setAddress("789 Oak St");

        // Het verwachte resultaat na de update
        CustomerDTO updatedResultDTO = new CustomerDTO(
                customerId,
                "John Updated",
                "Updated Company",
                "789 Oak St",
                "john.updated@example.com",
                "9876543210",
                2
        );

        // Mock het gedrag van de DAL
        when(customerDAL.findById(customerId)).thenReturn(Optional.of(existingCustomerDTO));
        when(customerDAL.updateCustomer(customerId, existingCustomerDTO)).thenReturn(updatedResultDTO);

        // Act
        CustomerDTO result = customerService.updateCustomer(customerId, updatedCustomerDTO);

        // Assert
        assertThat(result.getName()).isEqualTo("John Updated");
        assertThat(result.getCompany()).isEqualTo("Updated Company");
        assertThat(result.getEmail()).isEqualTo("john.updated@example.com");
        assertThat(result.getPhone()).isEqualTo("9876543210");
        assertThat(result.getAddress()).isEqualTo("789 Oak St");

        verify(customerDAL, times(1)).findById(customerId);
        verify(customerDAL, times(1)).updateCustomer(customerId, existingCustomerDTO);
    }

    @Test
    void testUpdateCustomerNotFound() {
        // Arrange
        int customerId = 1;

        // Nieuwe gegevens (UpdatedCustomerDTO die wordt meegegeven voor update)
        UpdatedCustomerDTO updatedCustomerDTO = new UpdatedCustomerDTO();
        updatedCustomerDTO.setName("John Updated");
        updatedCustomerDTO.setCompany("Updated Company");
        updatedCustomerDTO.setEmail("john.updated@example.com");
        updatedCustomerDTO.setPhone("9876543210");
        updatedCustomerDTO.setAddress("789 Oak St");

        // Simuleer dat de klant niet wordt gevonden
        when(customerDAL.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.updateCustomer(customerId, updatedCustomerDTO);
        });

        // Verifieer de foutmelding
        assertThat(exception.getMessage()).isEqualTo("Customer not found");

        // Verifieer interacties
        verify(customerDAL, times(1)).findById(customerId);
        verify(customerDAL, times(0)).updateCustomer(anyInt(), any(CustomerDTO.class));
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
