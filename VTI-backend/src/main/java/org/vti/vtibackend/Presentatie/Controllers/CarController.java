package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vti.vtibackend.BLL.Service.CarService;
import org.vti.vtibackend.model.Car.CarDTO;

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
        List<CarDTO> cars = carService.findCarsByCustomerId(customerId);
        return ResponseEntity.ok(cars);
    }
}
