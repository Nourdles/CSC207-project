package use_case.searchfilter;

import use_case.booksearch.BookSearchOutputData;

public interface SearchFilterOutputBoundary {
    void presentSearchFilterResponse(SearchFilterOutputData outputData);
}
