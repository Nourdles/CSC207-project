package interface_adapter.booksearch;
import entity.Book;
import java.util.ArrayList;

public class BookSearchState {
    private ArrayList<Book> searchResults;
    private String errorMessage = null;

    public BookSearchState() {}

    public ArrayList<Book> getSearchResults() {
        return searchResults;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setSearchResults(ArrayList<Book> searchResults) {
        this.searchResults = searchResults;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
