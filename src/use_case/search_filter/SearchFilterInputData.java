package use_case.search_filter;

import java.util.ArrayList;
import entity.Book;

public class SearchFilterInputData {
    final private String author;
    final private String year;
    final private String listings;
    final private ArrayList<Book> initialBooks;

    /**
     * Creates a new Search Filter Input Data with the given parameters
     * @param author String that represents the author the User wants to filter the results by
     * @param year String that represents the year the User wants to filter the results by
     * @param listings String that represents the number of Listings the User wants to filter the results by
     * @param initialBooks ArrayList of Books that represents the Books currently displayed in the Book Search View
     */
    public SearchFilterInputData(String author, String year, String listings, ArrayList<Book> initialBooks) {
        this.author = author;
        this.year = year;
        this.listings = listings;
        this.initialBooks = initialBooks;
    }

    /**
     * Returns the author to be filtered by stored in the Input Data
     * @return String that represents the author stored in the Input Data
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Returns the year to be filtered by stored in the Input Data
     * @return String that represents the year stored in the Input Data
     */
    public String getYear() {
        return this.year;
    }

    /**
     * Returns the number of Listings to be filtered by stored in the Input Data
     * @return String that represents the number of Listings stored in the Input Data
     */
    public String getListings() {
        return this.listings;
    }

    /**
     * Returns the list of Books currently displayed in the Book Search View, stored in the Input Data
     * @return ArrayList of Books currently stored in the Input Data
     */
    public ArrayList<Book> getInitialBooks() { return this.initialBooks;}
}
