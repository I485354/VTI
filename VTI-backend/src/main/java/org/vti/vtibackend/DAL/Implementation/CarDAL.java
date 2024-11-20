package org.vti.vtibackend.DAL.Implementation;

import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Car;
import org.vti.vtibackend.DAL.Interface.ICarDAL;
import org.vti.vtibackend.DAL.Repository.CarRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDAL implements ICarDAL {
    private final CarRepo carRepo;

    public CarDAL(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Optional<Car> findByCustomerId(int customerId){
        return carRepo.findById(customerId);
    }
}
