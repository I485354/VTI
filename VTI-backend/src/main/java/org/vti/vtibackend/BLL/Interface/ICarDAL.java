package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.Car.CarDTO;
import org.vti.vtibackend.model.Car.CreateCarDTO;

import java.util.List;
import java.util.Optional;

public interface ICarDAL {
    Optional<CarDTO> findByCustomerId(int customerId);
    List<CarDTO> findAll();
    CarDTO save(CarDTO carDTO);
}
