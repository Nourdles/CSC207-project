package use_case.book_search;

import entity.Book;

import java.util.ArrayList;

public interface BookSearchDataAccessInterface {

    ArrayList<Book> getSearchResult(String searchQuery);
}
