package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Notebook extends Stationary{
    private int pageCount;

    public Notebook() {
    }

    public Notebook(String brand, double price, int quantity, int pageCount) {
        super(brand, price, quantity);
        this.pageCount = pageCount;
        setProductId(UUID.randomUUID().toString());
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void edit(Scanner input) {
        super.edit(input);

        System.out.println("Edit Page count [" + this.pageCount + ":");
        setPageCount(getInput(input, this.pageCount));
    }

    @Override
    public void initialize(Scanner input){
        super.initialize(input);

        System.out.println("Enter Page count:");
        setPageCount(getInput(input, 0));
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "pages=" + pageCount + ", " + super.toString() +  '\'' +
                '}';
    }

    @Override
    public void sellItem(){
        System.out.println("Selling Notebook {pages: " + pageCount + super.toString() + "}");
        setQuantity(getQuantity() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Notebook notebook)) return false;
        if (!super.equals(o)) return false;
        return pageCount == notebook.pageCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pageCount);
    }
}