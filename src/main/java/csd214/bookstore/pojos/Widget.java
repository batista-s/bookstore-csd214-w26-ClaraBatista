package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Widget extends Product {
    private String widgetName;
    private double price;

    public Widget() {
        setWidgetName("Default Widget Name");
    }
    public Widget(String name, double price) {
        // set the productId
        setProductId(UUID.randomUUID().toString());

        setPrice(price);

        // this.name = name;
        setWidgetName(name); // this is better
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }


    @Override
    public void edit() {
        System.out.println("Enter Widget name (<"+getWidgetName()+">) : ");
        setWidgetName(getInput(getWidgetName()));
        System.out.println("Enter Widget price (<"+getPrice()+">) : ");
        setPrice(getInput(getPrice()));
    }

    @Override
    public void initialize() {
        System.out.println("Enter Widget name (<Default Widget Name>) : ");
        setWidgetName(getInput("Default Widget Name"));
        System.out.println("Enter Widget price (<0>) : ");
        setPrice(getInput(0.0));
    }

    @Override
    public void sellItem() {

    }

    @Override
    public double getPrice() {
        return 0;
    }
    public void setPrice(double price) {
        if(price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "widgetName='" + widgetName + '\'' +
                ", price=" + price +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Widget widget)) return false;
        return Double.compare(getPrice(), widget.getPrice()) == 0 && Objects.equals(getWidgetName(), widget.getWidgetName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWidgetName(), getPrice());
    }

}