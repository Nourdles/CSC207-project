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
            // Handle the case where the query is empty
            return;
        }

        // Create an input data object with the search query
        BookSearchInputData inputData = new BookSearchInputData(query);

        // Call the interactor (use case) to execute the search
        bookSearchCaseInteractor.execute(inputData);
    }
}
