package interface_adapter.search_filter;

import interface_adapter.book_search.BookSearchViewModel;
import use_case.search_filter.SearchFilterOutputBoundary;
import use_case.search_filter.SearchFilterOutputData;

public class SearchFilterPresenter implements SearchFilterOutputBoundary {
    private BookSearchViewModel viewModel;

    /**
     * Creates a new Search Filter Presenter with the given parameter
     * @param bookSearchViewModel Book Search View Model (we have no Search Filter View Model since no new View is
     *                            needed for this use case)
     */
    public SearchFilterPresenter(BookSearchViewModel bookSearchViewModel) {
        this.viewModel = bookSearchViewModel;
    }

    /**
     * If the isSuccess method of the Search Filter Output Data returns true, we call the updateSearchResults method of
     * the Book Search View Model, if not we call its updateErrorMessage method
     * @param outputData Search Filter Output Data
     */
    @Override
    public void presentSearchFilterResponse(SearchFilterOutputData outputData) {
        if (outputData.isSuccess()) {
            viewModel.updateSearchResults(outputData.getBooks());
        } else {
            viewModel.updateErrorMessage(outputData.getErrorMessage());
        }
    }
}
