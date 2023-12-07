package interface_adapter.book_info;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookInfoViewModelTest {
    private BookInfoViewModel viewModel = new BookInfoViewModel();

    @Test
    void setState() {
        BookInfoState state = new BookInfoState();
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    @Test
    void updateBookInfo() {
        List<List<String>> listings = new ArrayList<>();
        List<String> listing = new ArrayList<>();
        listing.add("Unu");
        listing.add("23.0");
        listing.add("Good");
        listing.add(null);
        listing.add(null);
        listing.add(null);
        listings.add(listing);
        BookInfoState state = new BookInfoState();
        viewModel.setState(state);
        viewModel.updateBookInfo("Philosopher's Stone", 1989, "J. K. Rowling", "123",
                "url", "en", new ArrayList<>(), listings);
        assertNull(viewModel.getState().getErrorMessage());
        assertEquals("Philosopher's Stone", viewModel.getState().getTitle());
        assertEquals("en", viewModel.getState().getLanguage());
        assertEquals("url", viewModel.getState().getCoverURL());
        assertEquals("J. K. Rowling", viewModel.getState().getAuthor());
        assertEquals("123", viewModel.getState().getISBN());
        assertEquals(1989, viewModel.getState().getYear());
        assertEquals(listings, viewModel.getState().getListingsInfo());
        assertEquals(new ArrayList<>(), viewModel.getState().getSubjects());
    }

    @Test
    void updateErrorMessage() {
        BookInfoState state = new BookInfoState();
        viewModel.setState(state);
        viewModel.updateErrorMessage("error");
        assertEquals("error", viewModel.getState().getErrorMessage());
        assertNull(viewModel.getState().getCoverURL());
        assertNull(viewModel.getState().getTitle());
        assertNull(viewModel.getState().getAuthor());
        assertNull(viewModel.getState().getISBN());
        assertNull(viewModel.getState().getLanguage());
        assertNull(viewModel.getState().getSubjects());
        assertNull(viewModel.getState().getListingsInfo());
    }

    @Test
    void getState() {
        BookInfoState state = new BookInfoState();
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }
}