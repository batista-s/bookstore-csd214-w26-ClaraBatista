package csd214.bookstore.pojos;

public class DigitalMusic extends MusicCollection{
    private String link;

    public DigitalMusic() {
    }

    public DigitalMusic(String title, String artist, String genre, int year, double price, String link) {
        super(title, artist, genre, year, price);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void edit() {
        super.edit();
        System.out.println("Edit Link [" + this.link + ":");
        setLink(getInput(this.link));
    }

    @Override
    public void initialize() {
        super.initialize();
        System.out.println("Enter Link:");
        setLink(getInput("No link attached"));
    }

    @Override
    public String toString() {
        return "Digital Music{" +
                "link='" + link + ", " + super.toString() +  '\'' +
                '}';
    }

    @Override
    public void sellItem(){
        System.out.println("Selling Digital Music (link: " + link + super.toString());
    }
}
