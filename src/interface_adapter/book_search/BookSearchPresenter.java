package interface_adapter.book_search;

import use_case.book_search.BookSearchOutputBoundary;
import use_case.book_search.BookSearchOutputData;

public class BookSearchPresenter implements BookSearchOutputBoundary{
    private BookSearchViewModel viewModel;

    /**
     * Create a new Book Search Presenter with the given parameters
     * @param bookSearchViewModel Book Search View Model
     */
    public BookSearchPresenter(BookSearchViewModel bookSearchViewModel) {
        this.viewModel = bookSearchViewModel;
    }

    /**
     * Calls the updateSearchResults method for the Book Search View Model if isSuccess returns true for Output Data,
     * and if not calls the updateErrorMessage method
     * @param outputData Book Search Output Data
     */
    @Override
    public void presentBookSearchResponse(BookSearchOutputData outputData) {
        if (outputData.isSuccess()) {
            viewModel.updateSearchResults(outputData.getBooks());
        } else {
            viewModel.updateErrorMessage(outputData.getErrorMessage());
        }
    }
}
