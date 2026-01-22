package csd214.bookstore.pojos;

import java.util.Scanner;

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
    public void edit(Scanner input) {
        super.edit(input);
        System.out.println("Edit Link [" + this.link + ":");
        setLink(getInput(input, this.link));
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);
        System.out.println("Enter Link:");
        setLink(getInput(input, "No link attached"));
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