package use_case.booksearch;
import entity.Book;

import java.util.ArrayList;

public class BookSearchOutputData {
    private ArrayList<Book> books;
    private String errorMessage;

    // Constructor for a successful search with results
    public BookSearchOutputData(ArrayList<Book> books) {
        this.books = books;
        this.errorMessage = null;
    }

    // Constructor for a failed search with an error message
    public BookSearchOutputData(String errorMessage) {
        this.errorMessage = errorMessage;
        this.books = null;
    }

    // Check if the search was successful
    public boolean isSuccess() {
        return errorMessage == null;
    }

    // Get the list of books
    public ArrayList<Book> getBooks() {
        if (isSuccess()) {
            return books;
        } else {
            throw new IllegalStateException("Attempted to get books when the search was not successful.");
        }
    }

    // Get the error message
    public String getErrorMessage() {
        if (!isSuccess()) {
            return errorMessage;
        } else {
            throw new IllegalStateException("Attempted to get error message when the search was successful.");
        }
    }
}
