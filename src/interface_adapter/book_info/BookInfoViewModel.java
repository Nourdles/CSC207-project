package interface_adapter.book_info;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class BookInfoViewModel extends ViewModel {
    public static final String viewName = "book info";
    private BookInfoState state = new BookInfoState();

    /**
     * Set the view name of the Book Info View Model
     */
    public BookInfoViewModel() {
        super("book info");
    }

    /**
     * Set the State for this View Model and alert that a change occurred
     * @param state Book Info State
     */
    public void setState(BookInfoState state) {
        this.state = state;
        firePropertyChanged();
    }

    /**
     * Update all the parameters of the Book Info State to the given parameters
     * @param title String we want to change the State title to
     * @param year int we want to change the State year to
     * @param author String we want to change the State author to
     * @param ISBN String we want to change the State ISBN to
     * @param coverURL String we want to change the url of the Book cover of the State to
     * @param language String we want to change the State language to
     * @param subjects Array List we want to change the State subjects to
     * @param listingsInfo List of List of Strings we want to change the State listingsInfo to
     */
    public void updateBookInfo(String title, int year, String author, String ISBN, String coverURL, String language, ArrayList<String> subjects, List<List<String>> listingsInfo) {
        state.setTitle(title);
        state.setYear(year);
        state.setAuthor(author);
        state.setISBN(ISBN);
        state.setCoverURL(coverURL);
        state.setLanguage(language);
        state.setSubjects(subjects);
        state.setListingsInfo(listingsInfo);
        state.setErrorMessage(null);
        firePropertyChanged();
    }

    /**
     * Update the error message of the State and put all its other parameters in an error state
     * @param errorMessage String we want to change the State error message to
     */
    public void updateErrorMessage(String errorMessage) {
        state.setErrorMessage(errorMessage);
        state.setTitle(null);
        state.setAuthor(null);
        state.setCoverURL(null);
        state.setISBN(null);
        state.setLanguage(null);
        state.setYear(0);
        state.setSubjects(null);
        firePropertyChanged();
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the current State of the Book Info use case
     * @return The current State of the Book Info use case
     */
    public BookInfoState getState() {
        return state;
    }
}
