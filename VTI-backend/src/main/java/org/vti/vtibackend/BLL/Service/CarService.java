package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Mapper.CarMapper;
import org.vti.vtibackend.DAL.Entity.Car;
import org.vti.vtibackend.DAL.Interface.ICarDAL;
import org.vti.vtibackend.model.CarDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final ICarDAL carDAL;
    private final CarMapper carMapper;

    @Autowired
    public CarService(ICarDAL carDAL,CarMapper carMapper) {
        this.carDAL = carDAL;
        this.carMapper = carMapper;

    }

    public List<CarDTO> findCarsByCustomerId(int customerId) {
        Optional<Car> cars = carDAL.findByCustomerId(customerId);
        return cars.stream()
                .map(carMapper::ToDTO)
                .collect(Collectors.toList());
    }
}
