package interface_adapter.book_search;

import entity.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookSearchViewModelTest {
    private BookSearchViewModel model = new BookSearchViewModel();

    @Test
    void setState() {
        BookSearchState state = new BookSearchState();
        state.setErrorMessage("error");
        model.setState(state);
        assertEquals(state, model.getState());
    }

    @Test
    void updateSearchResults() {
        BookSearchState state = new BookSearchState();
        model.setState(state);
        ArrayList<Book> initialBooks = new ArrayList<>();
        Book harryPotter1 = new Book("Philosopher's Stone", 1989, "J. K. Rowling", "summary",
                "123", 0, "URL", "english", new ArrayList<>());
        initialBooks.add(harryPotter1);
        model.updateSearchResults(initialBooks);
        assertEquals(initialBooks, model.getState().getSearchResults());
        assertNull(model.getState().getErrorMessage());
    }

    @Test
    void updateErrorMessage() {
        BookSearchState state = new BookSearchState();
        model.setState(state);
        model.updateErrorMessage("error");
        assertEquals("error", model.getState().getErrorMessage());
        assertNull(model.getState().getSearchResults());
    }

    @Test
    void getState() {
        BookSearchState state = new BookSearchState();
        state.setErrorMessage("error");
        model.setState(state);
        assertEquals(state, model.getState());
    }
}