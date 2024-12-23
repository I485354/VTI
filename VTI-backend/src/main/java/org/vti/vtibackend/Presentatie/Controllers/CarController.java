package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Service.CarService;
import org.vti.vtibackend.model.Car.CarDTO;
import org.vti.vtibackend.model.Car.CreateCarDTO;

import java.util.List;
@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CarDTO>> getCarsByCustomerId(@PathVariable int customerId) {
        System.out.println("Received customerId: " + customerId);
        List<CarDTO> cars = carService.findCarsByCustomerId(customerId);
        System.out.println("Cars retrieved: " + cars.size());
        return ResponseEntity.ok(cars);
    }
    @PostMapping()
    public ResponseEntity<CarDTO> createCar(@RequestBody CreateCarDTO carDTO) {
        CarDTO createdcarDTO = carService.createCar(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdcarDTO);

    }
}

