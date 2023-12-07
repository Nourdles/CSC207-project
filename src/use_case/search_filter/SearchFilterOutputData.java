package use_case.search_filter;

import entity.Book;

import java.util.ArrayList;

public class SearchFilterOutputData {
    final private ArrayList<Book> books;
    final private String errorMessage;

    /**
     * Create a new Search Filter Output Data for the given parameter, given no error occurred
     * @param books ArrayList of filtered Books to display in the Book Search View
     */
    public SearchFilterOutputData(ArrayList<Book> books) {
        this.books = books;
        this.errorMessage = null;
    }

    /**
     * Create a new Search Filter Output Data for the given parameter, given an error occurred
     * @param errorMessage String that represents the error message to display
     */
    public SearchFilterOutputData(String errorMessage) {
        this.errorMessage = errorMessage;
        this.books = null;
    }

    /**
     * Return whether no error occurred
     * @return boolean that represents whether no error occurred
     */
    public boolean isSuccess() {
        return errorMessage == null;
    }

    /**
     * Returns the list of Books to display, stored in Output Data if no error occurred in the use case, otherwise
     * throws an exception
     * @return ArrayList of Books stored in Output Data
     */
    public ArrayList<Book> getBooks() {
        if (isSuccess()) {
            return books;
        } else {
            throw new IllegalStateException("Attempted to get books when the search was not successful.");
        }
    }

    /**
     * Returns an error message is an error occurred in the use case, otherwise throws an exception
     * @return String that represents an error message stored in Output Data
     */
    public String getErrorMessage() {
        if (!isSuccess()) {
            return errorMessage;
        } else {
            throw new IllegalStateException("Attempted to get error message when the search was successful.");
        }
    }
}
