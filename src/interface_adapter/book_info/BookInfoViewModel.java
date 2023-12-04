package interface_adapter.book_info;

import interface_adapter.ViewModel;
import interface_adapter.booksearch.BookSearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class BookInfoViewModel extends ViewModel {
    public static final String viewName = "book info";
    private BookInfoState state = new BookInfoState();

    public BookInfoViewModel() {
        super("book info");
    }

    public void setState(BookInfoState state) {
        this.state = state;
        firePropertyChanged();
    }

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

    public BookInfoState getState() {
        return state;
    }
}
