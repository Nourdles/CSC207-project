package interface_adapter.book_info;

import use_case.book_info.BookInfoInputBoundary;
import use_case.book_info.BookInfoInputData;

import java.util.ArrayList;

public class BookInfoController {
    private final BookInfoInputBoundary bookInfoCaseInteractor;
    public BookInfoController(BookInfoInputBoundary bookInfoUseCaseInteractor) {
        this.bookInfoCaseInteractor = bookInfoUseCaseInteractor;
    }

    public void onBookSelected(String title, int year, String author, String ISBN, String coverURL, String language, ArrayList<String> subjects) {
        BookInfoInputData inputData = new BookInfoInputData(title, year, author, ISBN, coverURL, language, subjects);
        bookInfoCaseInteractor.showBookDetails(inputData);
    }
}
