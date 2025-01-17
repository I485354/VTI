package org.vti.vtibackend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vti.vtibackend.model.Customer.CustomerInfoDTO;
import org.vti.vtibackend.model.Invoice.InvoiceAndCustomerDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceAndCustomerDTOTest {

    private InvoiceAndCustomerDTO invoiceAndCustomerDTO;

    @BeforeEach
    void setUp() {
        // Initialiseer testdata
        List<CustomerInfoDTO> customerList = new ArrayList<>();
        customerList.add(new CustomerInfoDTO( "John Doe", "Doe Enterprises","john.doe@example.com", "123 Main St", "1234567890",1));
        customerList.add(new CustomerInfoDTO("Jane Smith", "Smith Motors","jane.smith@example.com", "456 Elm St", "0987654321",2));

        // Initialiseer het object met constructor
        invoiceAndCustomerDTO = new InvoiceAndCustomerDTO(
                1,                                // customer_id
                new Date(),                       // invoice_date
                new Date(System.currentTimeMillis() + 86400000), // due_date
                1000.50,                          // total_amount
                210.00,                           // total_btw
                "Pending",                        // status
                101,                              // invoice_number
                "false",                          // deleted
                customerList                      // customers
        );
    }

    @Test
    void testGetters() {
        // Controleer of de gegevens correct zijn ingesteld
        assertEquals(1, invoiceAndCustomerDTO.getCustomer_id());
        assertNotNull(invoiceAndCustomerDTO.getInvoice_date());
        assertNotNull(invoiceAndCustomerDTO.getDue_date());
        assertEquals(1000.50, invoiceAndCustomerDTO.getTotal_amount());
        assertEquals(210.00, invoiceAndCustomerDTO.getTotal_btw());
        assertEquals("Pending", invoiceAndCustomerDTO.getStatus());
        assertEquals(101, invoiceAndCustomerDTO.getInvoice_number());
        assertEquals("false", invoiceAndCustomerDTO.getDeleted());
        assertEquals(2, invoiceAndCustomerDTO.getCustomers().size());
    }

    @Test
    void testSetters() {
        // Wijzig de waarden via setters
        invoiceAndCustomerDTO.setCustomer_id(2);
        invoiceAndCustomerDTO.setInvoice_date(new Date(System.currentTimeMillis() - 86400000));
        invoiceAndCustomerDTO.setDue_date(new Date(System.currentTimeMillis() + 172800000));
        invoiceAndCustomerDTO.setTotal_amount(2000.75);
        invoiceAndCustomerDTO.setTotal_btw(420.00);
        invoiceAndCustomerDTO.setStatus("Paid");
        invoiceAndCustomerDTO.setInvoice_number(102);
        invoiceAndCustomerDTO.setDeleted("true");

        List<CustomerInfoDTO> newCustomerList = new ArrayList<>();
        newCustomerList.add(new CustomerInfoDTO("Emily Johnson", "Johnson Co.", "john.doe@example.com", "123 Main St", "1234567890", 3));
        invoiceAndCustomerDTO.setCustomers(newCustomerList);

        // Controleer of de wijzigingen correct zijn toegepast
        assertEquals(2, invoiceAndCustomerDTO.getCustomer_id());
        assertNotNull(invoiceAndCustomerDTO.getInvoice_date());
        assertNotNull(invoiceAndCustomerDTO.getDue_date());
        assertEquals(2000.75, invoiceAndCustomerDTO.getTotal_amount());
        assertEquals(420.00, invoiceAndCustomerDTO.getTotal_btw());
        assertEquals("Paid", invoiceAndCustomerDTO.getStatus());
        assertEquals(102, invoiceAndCustomerDTO.getInvoice_number());
        assertEquals("true", invoiceAndCustomerDTO.getDeleted());
        assertEquals(1, invoiceAndCustomerDTO.getCustomers().size());
        assertEquals("Emily Johnson", invoiceAndCustomerDTO.getCustomers().get(0).getName());
    }

    @Test
    void testDefaultConstructor() {
        // Maak een nieuw object aan met de default constructor
        InvoiceAndCustomerDTO emptyDTO = new InvoiceAndCustomerDTO();

        // Controleer of alle waarden null of standaard zijn
        assertEquals(0, emptyDTO.getCustomer_id());
        assertNull(emptyDTO.getInvoice_date());
        assertNull(emptyDTO.getDue_date());
        assertEquals(0.0, emptyDTO.getTotal_amount());
        assertEquals(0.0, emptyDTO.getTotal_btw());
        assertNull(emptyDTO.getStatus());
        assertEquals(0, emptyDTO.getInvoice_number());
        assertNull(emptyDTO.getDeleted());
        assertNull(emptyDTO.getCustomers());
    }
}