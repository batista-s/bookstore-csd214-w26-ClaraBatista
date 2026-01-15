package csd214.bookstore.pojos;

import java.util.Objects;

public abstract class Stationary extends Product {
    private String brand;
    private double price;
    private int quantity;

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
    public void initialize(){
        System.out.println("Enter Brand:");
        setBrand(getInput("Generic"));
        System.out.println("Enter Quantity:");
        setQuantity(getInput(0));
    }

    @Override
    public void edit() {
        // 2. Edit Self fields
        System.out.println("Edit brand [" + this.brand + "]:");

        // These two lines (27 & 28) do the same thing
        // this.brand = getInput(this.brand);
        setBrand(getInput("Generic"));
    }

    @Override
    public String toString() {
        return "Stationary{" +
                "brand='" + brand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stationary that)) return false;
        return Objects.equals(getBrand(), that.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getBrand());
    }
}
