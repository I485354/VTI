package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.ICarDAL;
import org.vti.vtibackend.model.Car.CarDTO;
import org.vti.vtibackend.model.Car.CreateCarDTO;

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

    public CarDTO createCar(CreateCarDTO createCarDTO) {
        // Validatie van de velden
        if (createCarDTO.getCustomer_id() <= 0
                || createCarDTO.getPlate_number() == null || createCarDTO.getPlate_number().isEmpty()
                || createCarDTO.getBrand() == null || createCarDTO.getBrand().isEmpty()
                || createCarDTO.getModel() == null || createCarDTO.getModel().isEmpty()
                || createCarDTO.getYear() <= 0
                || createCarDTO.getChasi_number() == null || createCarDTO.getChasi_number().isEmpty()) {
            throw new IllegalArgumentException("Alle velden (customer_id, plate_number, brand, model, year, chasi_number) zijn verplicht.");
        }

        // Maak een CarDTO-object
        CarDTO carDTO = new CarDTO();
        carDTO.setCustomer_id(createCarDTO.getCustomer_id());
        carDTO.setPlate_number(createCarDTO.getPlate_number());
        carDTO.setBrand(createCarDTO.getBrand());
        carDTO.setModel(createCarDTO.getModel());
        carDTO.setYear(createCarDTO.getYear());
        carDTO.setChasi_number(createCarDTO.getChasi_number());

        // Sla de auto op via de data access laag (DAL)
        return carDAL.save(carDTO);
    }

    public CarDTO findCarById(int carId) {
        return null;
    }

}
