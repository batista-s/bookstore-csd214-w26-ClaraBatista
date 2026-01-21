package csd214.bookstore.pojos;

public class Vinyl extends MusicCollection{
    private String discColor;
    private int copies;

    public Vinyl() {}

    public Vinyl(String title, String artist, String genre, int year, double price, String discColor, int copies) {
        super(title, artist, genre, year, price);
        this.discColor = discColor;
        this.copies = copies;
    }

    public String getDiscColor() {
        return discColor;
    }

    public void setDiscColor(String discColor) {
        this.discColor = discColor;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public void edit() {
        super.edit();
        System.out.println("Edit discColor [" + this.discColor + ":");
        setDiscColor(getInput(this.discColor));
        System.out.println("Edit Copies [" + this.copies + ":");
        setCopies(getInput(this.copies));
    }

    @Override
    public void initialize() {
        super.initialize();
        System.out.println("Enter discColor:");
        setDiscColor(getInput("Undefined"));
        System.out.println("Enter Copies:");
        setCopies(getInput(0));
    }


    @Override
    public void sellItem(){
        System.out.println("Selling Digital Music {color: " + discColor + super.toString() + "}");
        setCopies(getCopies()-1);
    }
}
