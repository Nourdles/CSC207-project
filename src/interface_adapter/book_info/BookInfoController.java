package interface_adapter.book_info;

import use_case.book_info.BookInfoInputBoundary;
import use_case.book_info.BookInfoInputData;

import java.util.ArrayList;

public class BookInfoController {
    private final BookInfoInputBoundary bookInfoCaseInteractor;

    /**
     * Returns a new Controller for the Book Info use case
     * @param bookInfoUseCaseInteractor the Interactor for the Book Info use case
     */
    public BookInfoController(BookInfoInputBoundary bookInfoUseCaseInteractor) {
        this.bookInfoCaseInteractor = bookInfoUseCaseInteractor;
    }

    /**
     * Creates a new Input Data class and calls the Interactor's showBookDetails() method
     * @param title title of the selected Book
     * @param year publication year of the selected Book
     * @param author author of the selected Book
     * @param ISBN ISBN of the selected Book
     * @param coverURL url of the cover of the selected Book
     * @param language language of the selected Book
     * @param subjects subjects of the selected Book
     */
    public void onBookSelected(String title, int year, String author, String ISBN, String coverURL, String language, ArrayList<String> subjects) {
        BookInfoInputData inputData = new BookInfoInputData(title, year, author, ISBN, coverURL, language, subjects);
        bookInfoCaseInteractor.showBookDetails(inputData);
    }
}
