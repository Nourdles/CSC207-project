package interface_adapter.book_search;

import use_case.book_search.BookSearchInputBoundary;
import use_case.book_search.BookSearchInputData;

public class BookSearchController {

    private final BookSearchInputBoundary bookSearchCaseInteractor;

    /**
     * Creates a new Book Search Controller with the given parameters
     * @param bookSearchUseCaseInteractor Book Search Input Boundary
     */
    public BookSearchController(BookSearchInputBoundary bookSearchUseCaseInteractor) {
        this.bookSearchCaseInteractor = bookSearchUseCaseInteractor;
    }

    /**
     * Creates new Input Data and call the execute method in the Interactor for the Book Search use case
     * @param query
     */
    public void onSearchButtonClicked(String query) {
        if (query == null || query.trim().isEmpty()) {
            return;
        }

        BookSearchInputData inputData = new BookSearchInputData(query);

        bookSearchCaseInteractor.execute(inputData);
    }
}
