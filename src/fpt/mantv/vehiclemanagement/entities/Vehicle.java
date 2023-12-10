package fpt.mantv.vehiclemanagement.entities;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private final long serialVersionUID = 741751L;
    private int id;
    private String name;
    private String color;
    private Double price;
    private String brand;
    private int productYear;
    public Vehicle() {}

    public Vehicle(int id, String name, String color, Double price, String brand, int productYear) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.productYear = productYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProductYear() {
        return productYear;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }
    @Override
    public String toString() {
        return String.format("Product Information: [ID: %d, Name: %s, Color: %s, Price: %.2f, Brand: %s, Product Year: %d]",
                id, name, color, price, brand, productYear);
    }

}
