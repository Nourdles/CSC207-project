package use_case.searchfilter;

import java.util.ArrayList;
import entity.Book;

import java.util.ArrayList;

public class SearchFilterInputData {
    final private String author;
    final private String year;
    final private String listings;
    final private ArrayList<Book> initialBooks;

    public SearchFilterInputData(String author, String year, String listings, ArrayList<Book> initialBooks) {
        this.author = author;
        this.year = year;
        this.listings = listings;
        this.initialBooks = initialBooks;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getYear() {
        return this.year;
    }

    public String getListings() {
        return this.listings;
    }

    public ArrayList<Book> getInitialBooks() { return this.initialBooks;}
}
