package org.vti.vtibackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int car_id;
    private int customer_id;
    private String plate_number;
    private String brand;
    private String model;
    private int year;
    private String chasi_number;

    @java.lang.SuppressWarnings("all")
    public int getCar_id() {
        return car_id;
    }

    @java.lang.SuppressWarnings("all")
    public int getCustomer_id() {
        return customer_id;
    }

    @java.lang.SuppressWarnings("all")
    public String getPlate_number() {
        return plate_number;
    }

    @java.lang.SuppressWarnings("all")
    public String getBrand() {
        return brand;
    }

    @java.lang.SuppressWarnings("all")
    public String getModel() {
        return model;
    }

    @java.lang.SuppressWarnings("all")
    public int getYear() {
        return year;
    }

    @java.lang.SuppressWarnings("all")
    public String getChasi_number() {
        return chasi_number;
    }

    @java.lang.SuppressWarnings("all")
    public void setCar_id(final int car_id) {
        this.car_id = car_id;
    }

    @java.lang.SuppressWarnings("all")
    public void setCustomer_id(final int customer_id) {
        this.customer_id = customer_id;
    }

    @java.lang.SuppressWarnings("all")
    public void setPlate_number(final String plate_number) {
        this.plate_number = plate_number;
    }

    @java.lang.SuppressWarnings("all")
    public void setBrand(final String brand) {
        this.brand = brand;
    }

    @java.lang.SuppressWarnings("all")
    public void setModel(final String model) {
        this.model = model;
    }

    @java.lang.SuppressWarnings("all")
    public void setYear(final int year) {
        this.year = year;
    }

    @java.lang.SuppressWarnings("all")
    public void setChasi_number(final String chasi_number) {
        this.chasi_number = chasi_number;
    }

}
