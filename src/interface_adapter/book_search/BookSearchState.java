package interface_adapter.book_search;
import entity.Book;
import java.util.ArrayList;

public class BookSearchState {
    private ArrayList<Book> searchResults;
    private String errorMessage = null;

    /**
     * Create a new State for the Book Search use case
     */
    public BookSearchState() {}

    /**
     * Return the search results stored in this State
     * @return An ArrayList of Books representing the search results stored in this State
     */
    public ArrayList<Book> getSearchResults() {
        return searchResults;
    }

    /**
     * Set the search results of this State
     * @param searchResults an ArrayList of Books representing the search results stored in this State
     */
    public void setSearchResults(ArrayList<Book> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * Set the error message this State should return if an error occurs
     * @param errorMessage A String representing the message that should be displayed if an error occurs
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the error message stored in the current State
     * @return String that represents the error message stored in the current State
     */
    public String getErrorMessage() {return this.errorMessage;}
}
