package interface_adapter.book_search;
import entity.Book;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class BookSearchViewModel extends ViewModel{

    public static final String viewName = "Book Search View";
    private BookSearchState state = new BookSearchState();

    /**
     * Create a new View Model for the Book Search use case with a given view name
     */
    public BookSearchViewModel() {
        super("book search");
    }

    /**
     * Set the State for this View Model and alert that a change occurred
     * @param state Book Search State
     */
    public void setState(BookSearchState state) {
        this.state = state;
        firePropertyChanged();
    }

    /**
     * Communicate the search results of the latest search to the current Book Search State
     * @param books An ArrayList of Books representing search results
     */
    public void updateSearchResults(ArrayList<Book> books) {
        state.setSearchResults(books);
        state.setErrorMessage(null);
        firePropertyChanged();
    }

    /**
     * Update the current Book Search State to an error state by communicating an appropriate error message and setting
     * the search results to null
     * @param errorMessage String representing the message that should be displayed if an error occurs
     */
    public void updateErrorMessage(String errorMessage) {
        state.setErrorMessage(errorMessage);
        state.setSearchResults(null);
        firePropertyChanged();
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the current Book Search State
     * @return The current Book Search State
     */
    public BookSearchState getState() {
        return state;
    }
}
