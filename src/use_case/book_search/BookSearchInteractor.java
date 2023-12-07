package use_case.book_search;
import entity.Book;

import java.util.ArrayList;

public class BookSearchInteractor implements BookSearchInputBoundary{

    final BookSearchDataAccessInterface searchDataAccessObject;

    final BookSearchOutputBoundary searchPresenter;

    /**
     * Create a new Book Search Interactor with the given parameters
     * @param bookSearchDataAccessInterface Book Search Data Access Interface
     * @param bookSearchOutputBoundary Book Search Output Boundary
     */
    public BookSearchInteractor(BookSearchDataAccessInterface bookSearchDataAccessInterface,
                                BookSearchOutputBoundary bookSearchOutputBoundary) {
        this.searchDataAccessObject = bookSearchDataAccessInterface;
        this.searchPresenter = bookSearchOutputBoundary;
    }

    /**
     * Generates an ArrayList of all the Books relevant to the search through the Data Access Object's getSearchResult
     * method, then creates a new Output Data and calls the Presenter's presentBookSearchResponse method. If an error
     * occurs, instead call the method on Output Data that represents an error state
     * @param bookSearchInputData Book Search Input Data
     */
    @Override
    public void execute(BookSearchInputData bookSearchInputData) {
        try {
            ArrayList<Book> books = searchDataAccessObject.getSearchResult(bookSearchInputData.getSearchQuery());

            BookSearchOutputData outputData = new BookSearchOutputData(books);
            searchPresenter.presentBookSearchResponse(outputData);
        } catch (Exception e) {
            BookSearchOutputData errorOutputData = new BookSearchOutputData(e.getMessage());
            searchPresenter.presentBookSearchResponse(errorOutputData);
        }
    }
}
