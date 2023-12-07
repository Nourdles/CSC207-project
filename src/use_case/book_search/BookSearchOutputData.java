package use_case.book_search;
import entity.Book;

import java.util.ArrayList;

public class BookSearchOutputData {
    private final ArrayList<Book> books;
    private final String errorMessage;

    /**
     * Create a new Output Data with the given parameter when no error has occurred
     * @param books ArrayList of Books representing the search results
     */
    public BookSearchOutputData(ArrayList<Book> books) {
        this.books = books;
        this.errorMessage = null;
    }

    /**
     * Create a new Output Data with the given parameter when an error has occurred
     * @param errorMessage String that represents the message to display if an error occurs
     */
    public BookSearchOutputData(String errorMessage) {
        this.errorMessage = errorMessage;
        this.books = null;
    }

    /**
     * Returns whether an error occurred in the use case
     * @return boolean representing whether an error occurred in the use case
     */
    public boolean isSuccess() {
        return errorMessage == null;
    }

    /**
     * Returns the list of Books to display as search results if no error occurred in the use case, otherwise throw an
     * exception
     * @return ArrayList of Books that represents the results of the search
     */
    public ArrayList<Book> getBooks() {
        if (isSuccess()) {
            return books;
        } else {
            throw new IllegalStateException("Attempted to get books when the search was not successful.");
        }
    }

    /**
     * Returns the error message if the use case was not a success, otherwise throw an exception
     * @return String that represents the error message
     */
    public String getErrorMessage() {
        if (!isSuccess()) {
            return errorMessage;
        } else {
            throw new IllegalStateException("Attempted to get error message when the search was successful.");
        }
    }
}
