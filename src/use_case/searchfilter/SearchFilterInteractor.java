package use_case.searchfilter;
import entity.Book;
import interface_adapter.searchfilter.SearchFilterPresenter;

import java.util.ArrayList;

public class SearchFilterInteractor implements SearchFilterInputBoundary{
    final SearchFilterOutputBoundary searchPresenter;

    public SearchFilterInteractor(SearchFilterOutputBoundary searchPresenter) {
        this.searchPresenter = searchPresenter;
    }
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

                // If all filters match, add the book to the filtered list
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
