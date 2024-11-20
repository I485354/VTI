package org.vti.vtibackend.DAL.Interface;

import org.vti.vtibackend.DAL.Entity.Car;

import java.util.List;
import java.util.Optional;

public interface ICarDAL {
    Optional<Car> findByCustomerId(int customerId);
}
