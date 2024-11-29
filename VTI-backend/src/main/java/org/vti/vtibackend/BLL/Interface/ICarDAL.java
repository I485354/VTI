package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.DAL.Entity.Car;
import org.vti.vtibackend.model.CarDTO;

import java.util.List;
import java.util.Optional;

public interface ICarDAL {
    Optional<CarDTO> findByCustomerId(int customerId);
    List<CarDTO> findAll();
    CarDTO save(CarDTO carDTO);
}
