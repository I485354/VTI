package org.vti.vtibackend.DAL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.DAL.Implementation.CustomerDAL;
import org.vti.vtibackend.DAL.Mapper.CustomerMapper;
import org.vti.vtibackend.DAL.Repository.CustomerRepo;
import org.vti.vtibackend.model.Customer.*; // import bijv. Customer, CustomerDTO, etc.

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerDALTest {

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerDAL customerDAL;

    private Customer customerEntity;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerEntity = new Customer();
        customerEntity.setCustomer_id(1);
        customerEntity.setName("ACME Inc");
        customerEntity.setEmail("info@acme.com");
        customerEntity.setPhone("123456789");
        customerEntity.setAddress("Main Street 1");

        customerDTO = new CustomerDTO();
        customerDTO.setCustomer_id(1);
        customerDTO.setName("ACME Inc");
        customerDTO.setEmail("info@acme.com");
        customerDTO.setPhone("123456789");
        customerDTO.setAddress("Main Street 1");
    }

    @Test
    void testSave() {
        // Arrange
        when(customerMapper.ToEntity(customerDTO)).thenReturn(customerEntity);
        when(customerRepo.save(customerEntity)).thenReturn(customerEntity);
        when(customerMapper.ToDTO(customerEntity)).thenReturn(customerDTO);

        // Act
        CustomerDTO result = customerDAL.save(customerDTO);

        // Assert
        assertNotNull(result);
        assertEquals("ACME Inc", result.getName());
        verify(customerMapper).ToEntity(customerDTO);
        verify(customerRepo).save(customerEntity);
        verify(customerMapper).ToDTO(customerEntity);
    }

    @Test
    void testCreateCustomer() {
        // Zelfde implementatie als save() volgens jouw DAL
        when(customerMapper.ToEntity(customerDTO)).thenReturn(customerEntity);
        when(customerRepo.save(customerEntity)).thenReturn(customerEntity);
        when(customerMapper.ToDTO(customerEntity)).thenReturn(customerDTO);

        CustomerDTO result = customerDAL.createCustomer(customerDTO);

        assertEquals("ACME Inc", result.getName());
        verify(customerMapper).ToEntity(customerDTO);
        verify(customerRepo).save(customerEntity);
    }

    @Test
    void testUpdateCustomer_Found() {
        // Arrange
        int id = 1;
        CustomerDTO updateDTO = new CustomerDTO();
        updateDTO.setName("New Name");
        updateDTO.setEmail("new@example.com");
        updateDTO.setPhone("987654321");
        updateDTO.setAddress("New Street 2");
        updateDTO.setCompany("New Company");

        when(customerRepo.findById(id)).thenReturn(Optional.of(customerEntity));

        Customer updatedEntity = new Customer();
        updatedEntity.setCustomer_id(1);
        updatedEntity.setName("New Name");
        updatedEntity.setEmail("new@example.com");
        updatedEntity.setPhone("987654321");
        updatedEntity.setAddress("New Street 2");
        updatedEntity.setCompany("New Company");

        when(customerRepo.save(any(Customer.class))).thenReturn(updatedEntity);


        CustomerDTO updatedDTO = new CustomerDTO();
        updatedDTO.setCustomer_id(1);
        updatedDTO.setName("New Name");
        updatedDTO.setEmail("new@example.com");
        updatedDTO.setPhone("987654321");
        updatedDTO.setAddress("New Street 2");
        updatedDTO.setCompany("New Company");

        when(customerMapper.ToDTO(updatedEntity)).thenReturn(updatedDTO);

        // Act
        CustomerDTO result = customerDAL.updateCustomer(id, updateDTO);

        // Assert
        assertEquals("New Name", result.getName());
        assertEquals("New Company", result.getCompany());
        verify(customerRepo).findById(id);
        verify(customerRepo).save(any(Customer.class));
        verify(customerMapper).ToDTO(updatedEntity);
    }

    @Test
    void testUpdateCustomer_NotFound() {
        int id = 999;
        CustomerDTO updateDTO = new CustomerDTO();
        updateDTO.setName("Missing Customer");

        when(customerRepo.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> customerDAL.updateCustomer(id, updateDTO));
        assertEquals("Customer not found", ex.getMessage());
        verify(customerRepo).findById(id);
        verify(customerRepo, never()).save(any(Customer.class));
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Customer> entities = Collections.singletonList(customerEntity);
        when(customerRepo.findAll()).thenReturn(entities);
        when(customerMapper.ToDTO(customerEntity)).thenReturn(customerDTO);

        // Act
        List<CustomerDTO> result = customerDAL.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("ACME Inc", result.get(0).getName());
        verify(customerRepo).findAll();
        verify(customerMapper).ToDTO(customerEntity);
    }

    @Test
    void testFindById_Found() {
        int id = 1;
        when(customerRepo.findById(id)).thenReturn(Optional.of(customerEntity));
        when(customerMapper.ToDTO(customerEntity)).thenReturn(customerDTO);

        Optional<CustomerDTO> result = customerDAL.findById(id);

        assertTrue(result.isPresent());
        assertEquals("ACME Inc", result.get().getName());
        verify(customerRepo).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        int id = 999;
        when(customerRepo.findById(id)).thenReturn(Optional.empty());

        Optional<CustomerDTO> result = customerDAL.findById(id);

        assertFalse(result.isPresent());
        verify(customerRepo).findById(id);
    }

    @Test
    void testDeleteById() {
        int id = 1;
        doNothing().when(customerRepo).deleteById(id);

        customerDAL.deleteById(id);

        verify(customerRepo).deleteById(id);
    }

    @Test
    void testFindHighestCustomerNumber() {
        when(customerRepo.findHighestCustomerNumber()).thenReturn(123);

        int highest = customerDAL.findHighestCustomerNumber();

        assertEquals(123, highest);
        verify(customerRepo).findHighestCustomerNumber();
    }

    @Test
    void testFindCustomerInfo() {
        List<Customer> entities = Collections.singletonList(customerEntity);

        when(customerRepo.findAll()).thenReturn(entities);


        CustomerInfoDTO infoDTO = new CustomerInfoDTO();
        infoDTO.setName("ACME Inc");
        when(customerMapper.ToInfoDTO(customerEntity)).thenReturn(infoDTO);

        List<CustomerInfoDTO> result = customerDAL.findCustomerInfo();

        assertEquals(1, result.size());
        assertEquals("ACME Inc", result.get(0).getName());
        verify(customerRepo).findAll();
        verify(customerMapper).ToInfoDTO(customerEntity);
    }
}