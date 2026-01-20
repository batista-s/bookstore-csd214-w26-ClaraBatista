package csd214.bookstore.pojos;

public class Notebook extends Stationary{
    private int pageCount;

    public Notebook() {
    }

    public Notebook(String brand, double price, int quantity, int pageCount) {
        super(brand, price, quantity);
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println("Edit Page count [" + this.pageCount + ":");
        setPageCount(getInput(this.pageCount));
    }

    @Override
    public void initialize(){
        super.initialize();

        System.out.println("Enter Page count:");
        setPageCount(getInput(0));
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "pages=" + pageCount + ", " + super.toString() +  '\'' +
                '}';
    }

    @Override
    public void sellItem(){
        System.out.println("Selling Notebook (pages: " + pageCount);
        setQuantity(getQuantity() - 1);
    }
}
