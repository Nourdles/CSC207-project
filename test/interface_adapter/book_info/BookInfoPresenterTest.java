package interface_adapter.book_info;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.book_info.BookInfoOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookInfoPresenterTest {
    private BookInfoViewModel viewModel = new BookInfoViewModel();
    private ViewManagerModel model = new ViewManagerModel();
    private BookInfoPresenter presenter = new BookInfoPresenter(viewModel, model);

    @Test
    void displayBookInfoSuccess() {
        List<List<String>> listings = new ArrayList<>();
        List<String> listing = new ArrayList<>();
        listing.add("Unu");
        listing.add("23.0");
        listing.add("Good");
        listing.add(null);
        listing.add(null);
        listing.add(null);
        listings.add(listing);
        BookInfoOutputData outputData = new BookInfoOutputData("Dracula", 1989, "Bram Stoker", "123", "url",
                "english", new ArrayList<>(), listings, null);
        presenter.displayBookInfo(outputData);
        BookInfoState state = viewModel.getState();
        assertEquals("url", state.getCoverURL());
        assertEquals("Dracula", state.getTitle());
        assertEquals("123", state.getISBN());
        assertEquals("english", state.getLanguage());
        assertEquals("Bram Stoker", state.getAuthor());
        assertEquals(new ArrayList<>(), state.getSubjects());
        assertEquals(1989, state.getYear());
        assertEquals(listings, state.getListingsInfo());
        assertEquals("book info", viewModel.getViewName());
    }

    @Test
    void displayBookInfoFailure() {
        List<List<String>> listings = new ArrayList<>();
        List<String> listing = new ArrayList<>();
        listing.add("Unu");
        listing.add("23.0");
        listing.add("Good");
        listing.add(null);
        listing.add(null);
        listing.add(null);
        listings.add(listing);
        BookInfoOutputData outputData = new BookInfoOutputData("Dracula", 1989, "Bram Stoker", "123", "url",
                "english", new ArrayList<>(), listings, "error");
        presenter.displayBookInfo(outputData);
        BookInfoState state = viewModel.getState();
        assertEquals("error", state.getErrorMessage());
    }
}