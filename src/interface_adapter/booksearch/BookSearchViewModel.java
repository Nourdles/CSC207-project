package interface_adapter.booksearch;
import entity.Book;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class BookSearchViewModel extends ViewModel{

    public static final String TITLE_LABEL = "Book Search View";
    public static final String SEARCH_LABEL = "Search";
    private BookSearchState state = new BookSearchState();

    public BookSearchViewModel() {
        super("book search");
    }

    public void setState(BookSearchState state) {
        this.state = state;
        firePropertyChanged();
    }

    public void updateSearchResults(ArrayList<Book> books) {
        state.setSearchResults(books);
        state.setErrorMessage(null);  // Clear any previous error messages
        firePropertyChanged();  // Notify observers about the change
    }

    public void updateErrorMessage(String errorMessage) {
        state.setErrorMessage(errorMessage);
        state.setSearchResults(null);  // Clear any previous search results
        firePropertyChanged();  // Notify observers about the change
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public BookSearchState getState() {
        return state;
    }
}
