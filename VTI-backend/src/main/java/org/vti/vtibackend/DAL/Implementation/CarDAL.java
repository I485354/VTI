package org.vti.vtibackend.DAL.Implementation;

import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Car;
import org.vti.vtibackend.BLL.Interface.ICarDAL;
import org.vti.vtibackend.DAL.Mapper.CarMapper;
import org.vti.vtibackend.DAL.Repository.CarRepo;
import org.vti.vtibackend.model.Car.CarDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CarDAL implements ICarDAL {
    private final CarRepo carRepo;
    private final CarMapper carMapper;

    public CarDAL(CarRepo carRepo, CarMapper carMapper) {
        this.carRepo = carRepo;
        this.carMapper = carMapper;
    }

    public Optional<CarDTO> findByCustomerId(int customerId){
        return carRepo.findByCustomerId(customerId)
                .map(carMapper::ToDTO);

    }
    public List<CarDTO> findAll(){
        return carRepo.findAll()
                .stream()
                .map(carMapper::ToDTO)
                .collect(Collectors.toList());
    }
    public CarDTO save(CarDTO carDTO){
        Car car = carMapper.ToEntity(carDTO);
        Car savedCar = carRepo.save(car);
        return carMapper.ToDTO(savedCar);
    }
}
