package org.vti.vtibackend.Presentatie;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.vti.vtibackend.Presentatie.Controllers.CustomerController;
import org.vti.vtibackend.model.Customer.*;
import org.vti.vtibackend.BLL.Service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private CustomerDTO customerDTO;
    private CreateCustomerDTO createCustomerDTO;
    private UpdatedCustomerDTO updatedCustomerDTO;
    private CustomerInfoDTO customerInfoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerDTO = new CustomerDTO(1, "John Doe", "Doe Enterprises", "johndoe@example.com", "123456789", "123 Main St",1);
        createCustomerDTO = new CreateCustomerDTO("Jane Doe", "Smith Co.", "janedoe@example.com", "987654321", "456 Elm St");
        updatedCustomerDTO = new UpdatedCustomerDTO("John Smith", "Updated Co.", "johnsmith@example.com", "543216789", "789 Oak St");
        customerInfoDTO = new CustomerInfoDTO("John Doe", "Doe Enterprises","johndoe@example.com", "123456789", "123 Main St",2);
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        List<CustomerDTO> customerList = new ArrayList<>();
        customerList.add(customerDTO);
        when(customerService.getAllCustomers()).thenReturn(customerList);

        // Act
        ResponseEntity<List<CustomerDTO>> response = customerController.getAllCustomers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getName());
        verify(customerService).getAllCustomers();
    }

    @Test
    void testGetCustomerById() {
        // Arrange
        int customerId = 1;
        when(customerService.getCustomerById(customerId)).thenReturn(customerDTO);

        // Act
        ResponseEntity<CustomerDTO> response = customerController.getCustomerById(customerId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
        verify(customerService).getCustomerById(customerId);
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        when(customerService.createCustomer(createCustomerDTO)).thenReturn(customerDTO);

        // Act
        ResponseEntity<CustomerDTO> response = customerController.createCustomer(createCustomerDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
        verify(customerService).createCustomer(createCustomerDTO);
    }

    @Test
    void testUpdateCustomer() {
        // Arrange
        int customerId = 1;
        when(customerService.updateCustomer(customerId, updatedCustomerDTO)).thenReturn(customerDTO);

        // Act
        ResponseEntity<CustomerDTO> response = customerController.updateCustomer(customerId, updatedCustomerDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
        verify(customerService).updateCustomer(customerId, updatedCustomerDTO);
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        int customerId = 1;
        doNothing().when(customerService).deleteCustomer(customerId);

        // Act
        customerController.deleteCustomer(customerId);

        // Assert
        verify(customerService).deleteCustomer(customerId);
    }

    @Test
    void testGetCustomerInfo() {
        // Arrange
        List<CustomerInfoDTO> customerInfoList = new ArrayList<>();
        customerInfoList.add(customerInfoDTO);
        when(customerService.getCustomerInfo()).thenReturn(customerInfoList);

        // Act
        ResponseEntity<List<CustomerInfoDTO>> response = customerController.getCustomerInfo();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getName());
        verify(customerService).getCustomerInfo();
    }
}