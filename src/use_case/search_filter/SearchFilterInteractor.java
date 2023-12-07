package use_case.search_filter;
import entity.Book;

import java.util.ArrayList;

public class SearchFilterInteractor implements SearchFilterInputBoundary{
    final SearchFilterOutputBoundary searchPresenter;

    /**
     * Create a new Search Filter Interactor with the given parameter
     * @param searchPresenter Search Filter Output Boundary
     */
    public SearchFilterInteractor(SearchFilterOutputBoundary searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    /**
     * Creates a new Array List of filtered Books, and if no error occurred, passes it to a new Output Data. If there
     * was an error, creates a new Output Data with an error message and calls the Present's presentSearchFilterResponse
     * method
     * @param searchFilterInputData Search Filter Input Data
     */
    @Override
    public void applyFilters(SearchFilterInputData searchFilterInputData) {
        try {
            String author = searchFilterInputData.getAuthor();
            String year = searchFilterInputData.getYear();
            String haslistings = searchFilterInputData.getListings();

            ArrayList<Book> initialBooks = searchFilterInputData.getInitialBooks();

            ArrayList<Book> filteredBooks = new ArrayList<>();

            for (Book book : initialBooks) {
                boolean authorFilter = author.isEmpty() || book.getAuthor().toLowerCase().contains(author.toLowerCase());
                boolean yearFilter = year.isEmpty() || Integer.toString(book.getYear()).contains(year);

                boolean hasListingsFilter = haslistings.equals("Both")
                        || (haslistings.equals("Yes") && book.getInStock() > 0)
                        || (haslistings.equals("No") && book.getInStock() == 0);

                if (authorFilter && yearFilter && hasListingsFilter) {
                    filteredBooks.add(book);
                }
            }
            SearchFilterOutputData outputData = new SearchFilterOutputData(filteredBooks);
            searchPresenter.presentSearchFilterResponse(outputData);
        }
        catch (Exception e) {
            SearchFilterOutputData errorOutputData = new SearchFilterOutputData(e.getMessage());
            searchPresenter.presentSearchFilterResponse(errorOutputData);
        }
    }
}
