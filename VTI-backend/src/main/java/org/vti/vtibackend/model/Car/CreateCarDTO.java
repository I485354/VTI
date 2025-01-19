package org.vti.vtibackend.model.Car;

public class CreateCarDTO {
    private int customer_id;
    private String plate_number;
    private String brand;
    private String model;
    private int year;
    private String chasi_number;

    public CreateCarDTO() {
    }

    public CreateCarDTO(int customer_id, String plate_number, String brand, String model, int year, String chasi_number) {
        this.customer_id = customer_id;
        this.plate_number = plate_number;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.chasi_number = chasi_number;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public String getChasi_number() {
        return chasi_number;
    }

    public void setChasi_number(String chasi_number) {
        this.chasi_number = chasi_number;
    }


}
