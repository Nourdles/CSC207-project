package interface_adapter.book_search;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.book_search.BookSearchInputBoundary;
import use_case.book_search.BookSearchInputData;

import static org.junit.jupiter.api.Assertions.*;

class BookSearchControllerTest {
    private BookSearchInputBoundary inputBoundaryMock;
    private BookSearchController controller;

    @Test
    void onSearchButtonClicked() {
        inputBoundaryMock = Mockito.mock(BookSearchInputBoundary.class);
        controller = new BookSearchController(inputBoundaryMock);
        controller.onSearchButtonClicked("search");
        Mockito.verify(inputBoundaryMock).execute(Mockito.any(BookSearchInputData.class));
    }
}