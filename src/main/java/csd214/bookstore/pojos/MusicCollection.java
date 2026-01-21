package csd214.bookstore.pojos;

import java.util.Objects;

public abstract class MusicCollection extends Product {
    private String title;
    private String artist;
    private String genre;
    private int year;
    private double price;

    public MusicCollection() {
    }

    public MusicCollection(String title, String artist, String genre, int year, double price) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void edit() {
        System.out.println("Edit Title [" + this.title + "]:");
        setTitle(getInput(this.title));
        System.out.println("Edit Artist [" + this.artist + "]:");
        setArtist(getInput(this.artist));
        System.out.println("Edit Genre [" + this.genre + "]:");
        setGenre(getInput(this.genre));
        System.out.println("Edit Year [" + this.year + "]:");
        setYear(getInput(this.year));
        System.out.println("Edit Price [" + this.price + "]:");
        setPrice(getInput(this.price));
    }

    @Override
    public void initialize() {
        System.out.println("Enter Title:");
        setTitle(getInput("Generic"));
        System.out.println("Enter Artist:");
        setArtist(getInput("Generic"));
        System.out.println("Enter Genre:");
        setGenre(getInput("Generic"));
        System.out.println("Enter Year:");
        setYear(getInput(0));
        System.out.println("Enter Price:");
        setPrice(getInput(0));
    }

    @Override
    public String toString() {
        return "MusicCollection{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MusicCollection that)) return false;
        return year == that.year && Double.compare(price, that.price) == 0 && Objects.equals(title, that.title) && Objects.equals(artist, that.artist) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, genre, year, price);
    }


}
