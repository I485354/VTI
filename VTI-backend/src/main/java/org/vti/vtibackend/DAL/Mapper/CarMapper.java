package org.vti.vtibackend.DAL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.Car;
import org.vti.vtibackend.model.CarDTO;


@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "car_id", target = "car_id")
    CarDTO ToDTO(Car car);

    @Mapping(source = "car_id", target = "car_id")
    Car ToEntity(CarDTO carDTO);
}
