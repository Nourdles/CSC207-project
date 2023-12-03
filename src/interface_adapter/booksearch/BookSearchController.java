package interface_adapter.booksearch;

import use_case.booksearch.BookSearchInputBoundary;
import use_case.booksearch.BookSearchInputData;

public class BookSearchController {

    private final BookSearchInputBoundary bookSearchCaseInteractor;
    public BookSearchController(BookSearchInputBoundary bookSearchUseCaseInteractor) {
        this.bookSearchCaseInteractor = bookSearchUseCaseInteractor;
    }

    public void onSearchButtonClicked(String query) {
        if (query == null || query.trim().isEmpty()) {
            return;
        }

        BookSearchInputData inputData = new BookSearchInputData(query);

        bookSearchCaseInteractor.execute(inputData);
    }
}
