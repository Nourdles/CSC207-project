package interface_adapter.booksearch;

import use_case.booksearch.BookSearchOutputBoundary;
import use_case.booksearch.BookSearchOutputData;

public class BookSearchPresenter implements BookSearchOutputBoundary{
    private BookSearchViewModel viewModel;

    public BookSearchPresenter(BookSearchViewModel bookSearchViewModel) {
        this.viewModel = bookSearchViewModel;
    }

    @Override
    public void presentBookSearchResponse(BookSearchOutputData outputData) {
        if (outputData.isSuccess()) {
            viewModel.updateSearchResults(outputData.getBooks());
        } else {
            viewModel.updateErrorMessage(outputData.getErrorMessage());
        }
    }
}
