package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Service.CarService;

import org.vti.vtibackend.BLL.Interface.ICarDAL;
import org.vti.vtibackend.model.Car.CarDTO;
import org.vti.vtibackend.model.Car.CreateCarDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CarServiceTest {
    @Mock
    private ICarDAL carDAL;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCarsByCustomerId_Found() {
        // GIVEN
        int customerId = 1;
        CarDTO car = new CarDTO();
        car.setCar_id(100);
        car.setCustomer_id(customerId);
        car.setPlate_number("ABC123");
        // Mock DAL to return an Optional with one CarDTO
        when(carDAL.findByCustomerId(customerId)).thenReturn(Optional.of(car));

        // WHEN
        List<CarDTO> result = carService.findCarsByCustomerId(customerId);

        // THEN
        assertEquals(1, result.size());
        assertEquals("ABC123", result.get(0).getPlate_number());
        verify(carDAL).findByCustomerId(customerId);
    }

    @Test
    void testFindCarsByCustomerId_NotFound() {
        // GIVEN
        int customerId = 999;
        // Mock returning empty Optional
        when(carDAL.findByCustomerId(customerId)).thenReturn(Optional.empty());

        // WHEN
        List<CarDTO> result = carService.findCarsByCustomerId(customerId);

        // THEN
        assertTrue(result.isEmpty());
        verify(carDAL).findByCustomerId(customerId);
    }

    @Test
    void testFindAllCars() {
        // GIVEN
        CarDTO car1 = new CarDTO();
        car1.setCar_id(10);
        CarDTO car2 = new CarDTO();
        car2.setCar_id(20);
        List<CarDTO> mockCars = List.of(car1, car2);

        when(carDAL.findAll()).thenReturn(mockCars);

        // WHEN
        List<CarDTO> result = carService.findAllCars();

        // THEN
        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getCar_id());
        assertEquals(20, result.get(1).getCar_id());
        verify(carDAL).findAll();
    }

    @Test
    void testCreateCar_ValidInput() {
        // GIVEN
        CreateCarDTO createCarDTO = new CreateCarDTO(
                1,             // customer_id
                "XYZ123",      // plate_number
                "Nissan",      // brand
                "GT-R",        // model
                2020,          // year
                "CHASIS-999"   // chasi_number
        );
        CarDTO savedCar = new CarDTO();
        savedCar.setCar_id(999);
        savedCar.setCustomer_id(createCarDTO.getCustomer_id());
        savedCar.setPlate_number(createCarDTO.getPlate_number());
        savedCar.setBrand(createCarDTO.getBrand());
        savedCar.setModel(createCarDTO.getModel());
        savedCar.setYear(createCarDTO.getYear());
        savedCar.setChasi_number(createCarDTO.getChasi_number());

        // Mock the DAL save to return our "savedCar"
        when(carDAL.save(any(CarDTO.class))).thenReturn(savedCar);

        // WHEN
        CarDTO result = carService.createCar(createCarDTO);

        // THEN
        assertNotNull(result);
        assertEquals(999, result.getCar_id());
        assertEquals("Nissan", result.getBrand());
        verify(carDAL).save(any(CarDTO.class));
    }

    @Test
    void testCreateCar_InvalidInput_ShouldThrowException() {
        // Example: missing plate_number
        CreateCarDTO invalidCarDTO = new CreateCarDTO(
                0,     // invalid customer_id
                null,  // plate_number is null
                "Nissan",
                "GT-R",
                2020,
                "CHASIS-999"
        );

        // WHEN + THEN
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> carService.createCar(invalidCarDTO),
                "Expected createCar() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Alle velden"));
        // No interaction with carDAL since it should fail on validation first
        verify(carDAL, never()).save(any(CarDTO.class));
    }

    @Test
    void testFindCarById_NotImplemented() {
        // Currently, findCarById always returns null
        CarDTO result = carService.findCarById(123);
        assertNull(result);
        // or throw an exception if you plan to implement it later
    }
}
