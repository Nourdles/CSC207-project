package interface_adapter.searchfilter;

import entity.Book;
import use_case.searchfilter.*;

import java.util.ArrayList;

public class SearchFilterController {
    private final SearchFilterInputBoundary searchFilterInputBoundary;

    public SearchFilterController(SearchFilterInputBoundary searchFilterInputBoundary) {
        this.searchFilterInputBoundary = searchFilterInputBoundary;
    }

    public void onFilterButtonClicked(String author, String year, String listings, ArrayList<Book> displayedBooks) {

        SearchFilterInputData searchFilterInputData = new SearchFilterInputData(author, year, listings, displayedBooks);
        searchFilterInputBoundary.applyFilters(searchFilterInputData);
    }
}
