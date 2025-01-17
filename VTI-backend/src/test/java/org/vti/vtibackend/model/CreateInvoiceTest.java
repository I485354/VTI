package org.vti.vtibackend.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vti.vtibackend.model.Invoice.CreateInvoiceDTO;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreateInvoiceTest {

    private CreateInvoiceDTO createInvoiceDTO;

    @BeforeEach
    void setUp() {
        // Initialiseer een nieuw object met de constructor
        createInvoiceDTO = new CreateInvoiceDTO(
                1,                         // customer_id
                2,                         // car_id
                new Date(),                // invoice_date
                new Date(System.currentTimeMillis() + 86400000), // due_date
                1000.50,                   // total_amount
                210.00,                    // total_btw
                "Pending"                  // status
        );
    }

    @Test
    void testConstructorAndGetters() {
        // Controleer of de constructorwaarden correct worden opgehaald
        assertEquals(1, createInvoiceDTO.getCustomer_id());
        assertEquals(2, createInvoiceDTO.getCar_id());
        assertNotNull(createInvoiceDTO.getInvoice_date());
        assertNotNull(createInvoiceDTO.getDue_date());
        assertEquals(1000.50, createInvoiceDTO.getTotal_amount());
        assertEquals(210.00, createInvoiceDTO.getTotal_btw());
        assertEquals("Pending", createInvoiceDTO.getStatus());
    }

    @Test
    void testSetters() {
        // Wijzig waarden met setters
        createInvoiceDTO.setCustomer_id(3);
        createInvoiceDTO.setCar_id(4);
        createInvoiceDTO.setInvoice_date(new Date(System.currentTimeMillis() - 86400000));
        createInvoiceDTO.setDue_date(new Date(System.currentTimeMillis() + 172800000));
        createInvoiceDTO.setTotal_amount(2000.75);
        createInvoiceDTO.setTotal_btw(420.00);
        createInvoiceDTO.setStatus("Paid");

        // Controleer of de waarden correct zijn ingesteld
        assertEquals(3, createInvoiceDTO.getCustomer_id());
        assertEquals(4, createInvoiceDTO.getCar_id());
        assertNotNull(createInvoiceDTO.getInvoice_date());
        assertNotNull(createInvoiceDTO.getDue_date());
        assertEquals(2000.75, createInvoiceDTO.getTotal_amount());
        assertEquals(420.00, createInvoiceDTO.getTotal_btw());
        assertEquals("Paid", createInvoiceDTO.getStatus());
    }

    @Test
    void testDefaultConstructor() {
        // Maak een nieuw object met de default constructor
        CreateInvoiceDTO defaultInvoiceDTO = new CreateInvoiceDTO();

        // Controleer of alle velden de standaardwaarden hebben
        assertEquals(0, defaultInvoiceDTO.getCustomer_id());
        assertNull(defaultInvoiceDTO.getCar_id());
        assertNull(defaultInvoiceDTO.getInvoice_date());
        assertNull(defaultInvoiceDTO.getDue_date());
        assertEquals(0.0, defaultInvoiceDTO.getTotal_amount());
        assertEquals(0.0, defaultInvoiceDTO.getTotal_btw());
        assertNull(defaultInvoiceDTO.getStatus());
    }
}

