package org.vti.vtibackend.model.Car;




public class CarInfoDTO {

    private String plate_number;
    private String brand;
    private String model;
    private int year;

    public CarInfoDTO() {

    }
    public CarInfoDTO(String plate_number, String brand, String model, int year) {
        this.plate_number = plate_number;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getPlate_number() {
        return plate_number;
    }
    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
