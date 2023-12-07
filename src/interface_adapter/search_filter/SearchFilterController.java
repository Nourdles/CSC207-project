package interface_adapter.search_filter;

import entity.Book;
import use_case.search_filter.*;

import java.util.ArrayList;

public class SearchFilterController {
    private final SearchFilterInputBoundary searchFilterInputBoundary;

    /**
     * Creates a new Search Filter Controller with the given parameter
     * @param searchFilterInputBoundary Search Filter Input Boundary
     */
    public SearchFilterController(SearchFilterInputBoundary searchFilterInputBoundary) {
        this.searchFilterInputBoundary = searchFilterInputBoundary;
    }

    /**
     * Creates a new Input Data for the Search Filter use case and calls the applyFilters method of the Interactor
     * @param author String representing the author we want to filter by
     * @param year String representing the year we want to filter by
     * @param listings String representing the number of listings we want to filter by
     * @param displayedBooks ArrayList of the Books currently displayed in the Book Search View
     */
    public void onFilterButtonClicked(String author, String year, String listings, ArrayList<Book> displayedBooks) {

        SearchFilterInputData searchFilterInputData = new SearchFilterInputData(author, year, listings, displayedBooks);
        searchFilterInputBoundary.applyFilters(searchFilterInputData);
    }
}
