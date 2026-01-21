package csd214.bookstore.pojos;

import java.util.Objects;

public class Pen extends Stationary {
    private String color;

    public Pen() {
    }

    public Pen(String brand, double price, int quantity, String color) {
        super(brand, price, quantity);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void initialize(){
        super.initialize();

        System.out.println("Enter Color:");
        setColor(getInput("Undefined"));
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println("Edit Color [" + this.color + ":");
        setColor(getInput(this.color));
    }

    @Override
    public String toString() {
        return "Pen{" +
                "color='" + color + ", " + super.toString() +  '\'' +
                '}';
    }

    @Override
    public void sellItem(){
        System.out.println("Selling Pen {color: " + color + super.toString() + "}");
        setQuantity(getQuantity() - 1);
    }

}
