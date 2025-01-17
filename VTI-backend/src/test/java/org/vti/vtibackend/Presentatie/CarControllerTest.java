package org.vti.vtibackend.Presentatie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.vti.vtibackend.BLL.Service.CarService;
import org.vti.vtibackend.Presentatie.Controllers.CarController;
import org.vti.vtibackend.model.Car.CarDTO;
import org.vti.vtibackend.model.Car.CreateCarDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private CarDTO carDTO;
    private CreateCarDTO createCarDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        carDTO = new CarDTO();
        carDTO.setCustomer_id(1);
        carDTO.setPlate_number("ABC-123");
        carDTO.setBrand("Toyota");
        carDTO.setModel("Corolla");
        carDTO.setYear(2021);
        carDTO.setChasi_number("CHASIS-001");

        createCarDTO = new CreateCarDTO(1, "DEF-456", "Honda", "Civic", 2022, "CHASIS-002");
    }

    @Test
    void testGetCarsByCustomerId() {
        // Arrange
        int customerId = 1;
        List<CarDTO> cars = new ArrayList<>();
        cars.add(carDTO);

        when(carService.findCarsByCustomerId(customerId)).thenReturn(cars);

        // Act
        ResponseEntity<List<CarDTO>> response = carController.getCarsByCustomerId(customerId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Toyota", response.getBody().get(0).getBrand());
        verify(carService).findCarsByCustomerId(customerId);
    }

    @Test
    void testGetCarsByCustomerId_NoCarsFound() {
        // Arrange
        int customerId = 1;
        when(carService.findCarsByCustomerId(customerId)).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<CarDTO>> response = carController.getCarsByCustomerId(customerId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(carService).findCarsByCustomerId(customerId);
    }

    @Test
    void testCreateCar() {
        // Arrange
        when(carService.createCar(createCarDTO)).thenReturn(carDTO);

        // Act
        ResponseEntity<CarDTO> response = carController.createCar(createCarDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Toyota", response.getBody().getBrand());
        assertEquals("ABC-123", response.getBody().getPlate_number());
        verify(carService).createCar(createCarDTO);
    }
}
