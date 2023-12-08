package interface_adapter.book_search;

import entity.Book;
import org.junit.jupiter.api.Test;
import use_case.book_search.BookSearchOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookSearchPresenterTest {
    private BookSearchViewModel viewModel = new BookSearchViewModel();
    private  BookSearchPresenter presenter = new BookSearchPresenter(viewModel);

    @Test
    void presentBookSearchResponseSuccess() {
        ArrayList<Book> initialBooks = new ArrayList<>();
        Book harryPotter1 = new Book("Philosopher's Stone", 1989, "J. K. Rowling", "summary",
                "123", 0, "URL", "english", new ArrayList<>());
        initialBooks.add(harryPotter1);
        BookSearchOutputData outputData = new BookSearchOutputData(initialBooks);
        presenter.presentBookSearchResponse(outputData);
        BookSearchState state = viewModel.getState();

        assertEquals(initialBooks, state.getSearchResults());
    }

    @Test
    void presentBookSearchResponseFailure() {
        BookSearchOutputData outputData = new BookSearchOutputData("error");
        presenter.presentBookSearchResponse(outputData);
        BookSearchState state = viewModel.getState();

        assertEquals("error", state.getErrorMessage());
    }
}