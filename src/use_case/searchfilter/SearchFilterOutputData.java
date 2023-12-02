package use_case.searchfilter;

import entity.Book;

import java.util.ArrayList;

public class SearchFilterOutputData {
    final private ArrayList<Book> books;
    final private String errorMessage;

    public SearchFilterOutputData(ArrayList<Book> books) {
        this.books = books;
        this.errorMessage = null;
    }

    public SearchFilterOutputData(String errorMessage) {
        this.errorMessage = errorMessage;
        this.books = null;
    }

    public boolean isSuccess() {
        return errorMessage == null;
    }

    public ArrayList<Book> getBooks() {
        if (isSuccess()) {
            return books;
        } else {
            throw new IllegalStateException("Attempted to get books when the search was not successful.");
        }
    }

    public String getErrorMessage() {
        if (!isSuccess()) {
            return errorMessage;
        } else {
            throw new IllegalStateException("Attempted to get error message when the search was successful.");
        }
    }
}
