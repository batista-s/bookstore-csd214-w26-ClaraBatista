package csd214.bookstore.pojos;

import java.util.Objects;

public abstract class Stationary extends Product {
    private String brand;

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void initialize(){
        System.out.println("Enter Brand:");
        this.brand = getInput("Generic");
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
