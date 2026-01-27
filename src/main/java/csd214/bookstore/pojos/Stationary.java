package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public abstract class Stationary extends Product {
    private String brand;
    private double price;
    private int quantity;

    public Stationary() {    }

    public Stationary(String brand, double price, int quantity) {
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void initialize(Scanner input){
        System.out.println("Enter Brand:");
        setBrand(getInput(input, "Generic"));
        System.out.println("Enter Price:");
        setPrice(getInput(input, 0.0));
        System.out.println("Enter Quantity:");
        setQuantity(getInput(input, 0));
    }

    @Override
    public void edit(Scanner input) {
        // 2. Edit Self fields
        System.out.println("Edit brand [" + this.brand + "]:");

        // These two lines (27 & 28) do the same thing
        // this.brand = getInput(this.brand);
        setBrand(getInput(input, "Generic"));

        System.out.println("Edit Quantity [" + this.brand + "]:");
        setQuantity(getInput(input, 0));

        System.out.println("Edit Price [" + this.brand + "]:");
        setPrice(getInput(input, 0));
    }

    @Override
    public String toString() {
        return "Stationary{" +
                "brand='" + brand + '\'' +
                "price='" + price + '\'' +
                "quantity='" + quantity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stationary that)) return false;
        return Double.compare(price, that.price) == 0 && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, price);
    }
}