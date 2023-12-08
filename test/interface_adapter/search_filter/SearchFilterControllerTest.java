package interface_adapter.search_filter;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.search_filter.SearchFilterInputBoundary;
import use_case.search_filter.SearchFilterInputData;

import java.util.ArrayList;

class SearchFilterControllerTest {
    private SearchFilterInputBoundary inputBoundaryMock;
    private SearchFilterController controller;

    @Test
    void onFilterButtonClicked() {
        inputBoundaryMock = Mockito.mock(SearchFilterInputBoundary.class);
        controller = new SearchFilterController(inputBoundaryMock);
        controller.onFilterButtonClicked("author", "2023", "listings", new ArrayList<>());
        Mockito.verify(inputBoundaryMock).applyFilters(Mockito.any(SearchFilterInputData.class));
    }
}