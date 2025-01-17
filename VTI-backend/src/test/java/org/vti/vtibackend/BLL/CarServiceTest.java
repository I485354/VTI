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


import java.util.List;
import java.util.Optional;


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

        int customerId = 1;
        CarDTO car = new CarDTO();
        car.setCar_id(100);
        car.setCustomer_id(customerId);
        car.setPlate_number("ABC123");

        when(carDAL.findByCustomerId(customerId)).thenReturn(Optional.of(car));


        List<CarDTO> result = carService.findCarsByCustomerId(customerId);


        assertEquals(1, result.size());
        assertEquals("ABC123", result.get(0).getPlate_number());
        verify(carDAL).findByCustomerId(customerId);
    }

    @Test
    void testFindCarsByCustomerId_NotFound() {

        int customerId = 999;

        when(carDAL.findByCustomerId(customerId)).thenReturn(Optional.empty());


        List<CarDTO> result = carService.findCarsByCustomerId(customerId);


        assertTrue(result.isEmpty());
        verify(carDAL).findByCustomerId(customerId);
    }

    @Test
    void testFindAllCars() {

        CarDTO car1 = new CarDTO();
        car1.setCar_id(10);
        CarDTO car2 = new CarDTO();
        car2.setCar_id(20);
        List<CarDTO> mockCars = List.of(car1, car2);

        when(carDAL.findAll()).thenReturn(mockCars);


        List<CarDTO> result = carService.findAllCars();


        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getCar_id());
        assertEquals(20, result.get(1).getCar_id());
        verify(carDAL).findAll();
    }

    @Test
    void testCreateCar_ValidInput() {

        CreateCarDTO createCarDTO = new CreateCarDTO(
                1,
                "XYZ123",
                "Nissan",
                "GT-R",
                2020,
                "CHASIS-999"
        );
        CarDTO savedCar = new CarDTO();
        savedCar.setCar_id(999);
        savedCar.setCustomer_id(createCarDTO.getCustomer_id());
        savedCar.setPlate_number(createCarDTO.getPlate_number());
        savedCar.setBrand(createCarDTO.getBrand());
        savedCar.setModel(createCarDTO.getModel());
        savedCar.setYear(createCarDTO.getYear());
        savedCar.setChasi_number(createCarDTO.getChasi_number());


        when(carDAL.save(any(CarDTO.class))).thenReturn(savedCar);


        CarDTO result = carService.createCar(createCarDTO);


        assertNotNull(result);
        assertEquals(999, result.getCar_id());
        assertEquals("Nissan", result.getBrand());
        verify(carDAL).save(any(CarDTO.class));
    }

    @Test
    void testCreateCar_InvalidInput_ShouldThrowException() {

        CreateCarDTO invalidCarDTO = new CreateCarDTO(
                0,
                null,
                "Nissan",
                "GT-R",
                2020,
                "CHASIS-999"
        );

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> carService.createCar(invalidCarDTO),
                "Expected createCar() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Alle velden"));

        verify(carDAL, never()).save(any(CarDTO.class));
    }

    @Test
    void testFindCarById_NotImplemented() {
        CarDTO result = carService.findCarById(123);
        assertNull(result);
    }
}
