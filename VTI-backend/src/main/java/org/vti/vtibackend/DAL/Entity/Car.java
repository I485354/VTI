package org.vti.vtibackend.DAL.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int car_id;
    private int customer_id;
    private String plate_number;
    private String brand;
    private String model;
    private int year;
    private String chasi_number;

    public Car() {
    }

    public Car(int car_id, int customer_id, String plate_number, String brand, String model, int year, String chasi_number) {
        this.car_id = car_id;
        this.customer_id = customer_id;
        this.plate_number = plate_number;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.chasi_number = chasi_number;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(final int car_id) {
        this.car_id = car_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(final int customer_id) {
        this.customer_id = customer_id;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(final String plate_number) {
        this.plate_number = plate_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public String getChasi_number() {
        return chasi_number;
    }

    public void setChasi_number(final String chasi_number) {
        this.chasi_number = chasi_number;
    }
}
