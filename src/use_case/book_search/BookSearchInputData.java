package use_case.book_search;

public class BookSearchInputData {
    final private String searchQuery;

    /**
     * Create a new Book Search Input Data with the given parameter
     * @param searchQuery String that represents the text inputted by the User in the search bar
     */
    public BookSearchInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * Returns the text entered by the User into the search bar, stored in Input Data
     * @return String that represents the search query stored in the current Input Data
     */
    String getSearchQuery() {return searchQuery;}
}
