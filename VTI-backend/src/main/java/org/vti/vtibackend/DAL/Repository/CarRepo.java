package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vti.vtibackend.DAL.Entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepo extends JpaRepository<Car,Integer> {
    @Query("SELECT c FROM Car c where c.customer_id = :customerId")
    Optional<Car> findByCustomerId(int customerId);
}
