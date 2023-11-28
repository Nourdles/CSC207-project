package use_case.booksearch;

public class BookSearchInputData {
    final private String searchQuery;

    public BookSearchInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    String getSearchQuery() {return searchQuery;}
}
