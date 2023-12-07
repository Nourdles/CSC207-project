package interface_adapter.book_search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookSearchStateTest {
    private BookSearchState state = new BookSearchState();

    @Test
    void getSearchResults() {
        assertNull(state.getSearchResults());
    }

    @Test
    void setSearchResults() {
        state.setSearchResults(new ArrayList<>());
        assertEquals(new ArrayList<>(), state.getSearchResults());
    }

    @Test
    void setErrorMessage() {
        state.setErrorMessage("error");
        assertEquals("error", state.getErrorMessage());
    }

    @Test
    void getErrorMessage() {
        assertNull(state.getErrorMessage());
    }
}