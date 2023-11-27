package use_case.booksearch;

import entity.Book;

import java.util.ArrayList;

public interface BookSearchDataAccessInterface {

    ArrayList<Book> getSearchResult(String searchQuery);
}
