package interface_adapter.searchfilter;

import interface_adapter.booksearch.BookSearchViewModel;
import use_case.searchfilter.SearchFilterOutputBoundary;
import use_case.searchfilter.SearchFilterOutputData;

public class SearchFilterPresenter implements SearchFilterOutputBoundary {
    private BookSearchViewModel viewModel;

    public SearchFilterPresenter(BookSearchViewModel bookSearchViewModel) {
        this.viewModel = bookSearchViewModel;
    }
    @Override
    public void presentSearchFilterResponse(SearchFilterOutputData outputData) {
        if (outputData.isSuccess()) {
            viewModel.updateSearchResults(outputData.getBooks());
        } else {
            viewModel.updateErrorMessage(outputData.getErrorMessage());
        }
    }
}
