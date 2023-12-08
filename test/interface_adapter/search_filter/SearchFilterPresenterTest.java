package interface_adapter.search_filter;

import entity.Book;
import interface_adapter.book_search.BookSearchViewModel;
import org.junit.jupiter.api.Test;
import use_case.search_filter.SearchFilterOutputData;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchFilterPresenterTest {
    private final BookSearchViewModel viewModel = new BookSearchViewModel();
    private SearchFilterPresenter presenter = new SearchFilterPresenter(viewModel);

    @Test
    void presentSearchFilterResponseSuccess() {
        ArrayList<Book> initialBooks = new ArrayList<>();
        File file = new File("demo.txt");
        Book harryPotter1 = new Book("Philosopher's Stone", 1989, "J. K. Rowling", "summary",
                "123", 0, "URL", "english", new ArrayList<>());
        initialBooks.add(harryPotter1);
        SearchFilterOutputData outputData = new SearchFilterOutputData(initialBooks);
        presenter.presentSearchFilterResponse(outputData);
        assertEquals(initialBooks, viewModel.getState().getSearchResults());
    }

    @Test
    void presentSearchFilterResponseFailure() {
        SearchFilterOutputData outputData = new SearchFilterOutputData("error");
        presenter.presentSearchFilterResponse(outputData);
        assertEquals("error", viewModel.getState().getErrorMessage());
    }
}