package src.entity;

import java.util.ArrayList;

public class Book {

    private final String title;
    private final String genre;
    private final int year;
    private final String author;
    private final String summary;
    private final int ISBN;
    private final int inStock;

    Book(String title, String genre, int year, String author, String summary, int ISBN, int inStock) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.author = author;
        this.summary = summary;
        this.ISBN = ISBN;
        this.inStock = inStock;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }


    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public int getISBN() {
        return ISBN;
    }

    public int getInStock() {
        return inStock;
    }
}
