package org.vti.vtibackend.model;

import org.junit.jupiter.api.Test;
import org.vti.vtibackend.model.Car.CreateCarDTO;

import static org.junit.jupiter.api.Assertions.*;

class CreateCarTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int customerId = 1;
        String plateNumber = "ABC-123";
        String brand = "Toyota";
        String model = "Corolla";
        int year = 2021;
        String chasiNumber = "CHASIS-001";

        // Act
        CreateCarDTO carDTO = new CreateCarDTO(customerId, plateNumber, brand, model, year, chasiNumber);

        // Assert
        assertEquals(customerId, carDTO.getCustomer_id());
        assertEquals(plateNumber, carDTO.getPlate_number());
        assertEquals(brand, carDTO.getBrand());
        assertEquals(model, carDTO.getModel());
        assertEquals(year, carDTO.getYear());
        assertEquals(chasiNumber, carDTO.getChasi_number());
    }

    @Test
    void testSetters() {
        // Arrange
        CreateCarDTO carDTO = new CreateCarDTO();

        // Act
        carDTO.setCustomer_id(1);
        carDTO.setPlate_number("DEF-456");
        carDTO.setBrand("Honda");
        carDTO.setModel("Civic");
        carDTO.setYear(2022);
        carDTO.setChasi_number("CHASIS-002");

        // Assert
        assertEquals(1, carDTO.getCustomer_id());
        assertEquals("DEF-456", carDTO.getPlate_number());
        assertEquals("Honda", carDTO.getBrand());
        assertEquals("Civic", carDTO.getModel());
        assertEquals(2022, carDTO.getYear());
        assertEquals("CHASIS-002", carDTO.getChasi_number());
    }
}
