package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.ICarDAL;
import org.vti.vtibackend.model.Car.CarDTO;

import java.util.List;
import java.util.Optional;


@Service
public class CarService {
    private final ICarDAL carDAL;


    @Autowired
    public CarService(ICarDAL carDAL) {
        this.carDAL = carDAL;

    }

    public List<CarDTO> findCarsByCustomerId(int customerId) {
        Optional<CarDTO> cars = carDAL.findByCustomerId(customerId);
        return cars.stream().toList();
    }
    public List<CarDTO> findAllCars() {
        return carDAL.findAll();
    }

    public CarDTO createCar(CarDTO carDTO) {
        return carDAL.save(carDTO);
    }
    public CarDTO findCarById(int carId) {
        return null;
    }
}
