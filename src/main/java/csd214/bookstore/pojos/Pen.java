package csd214.bookstore.pojos;

import java.util.Objects;

public class Pen extends Stationary {
    private String color;

    @Override
    public void initialize(){
        super.initialize();

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Pen{" +
                "color='" + color + '\'' +
                '}';
    }

    @Override
    public void sellItem(){

    }

    @Override
    public double getPrice(){
        return getPrice
    }

}
