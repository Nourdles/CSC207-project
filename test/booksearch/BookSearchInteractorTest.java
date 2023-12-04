package booksearch;
import data_access.OpenLibraryDB;
import org.junit.jupiter.api.Test;
import use_case.book_info.BookInfoDataAccessInterface;
import use_case.booksearch.*;

import static org.junit.jupiter.api.Assertions.*;
public class BookSearchInteractorTest {
    @Test
    void failtest(){
        BookSearchInputData inputData = new BookSearchInputData("darcula");
        BookSearchDataAccessInterface userRepo = new OpenLibraryDB();
        BookSearchOutputBoundary failurePresenter = new BookSearchOutputBoundary() {
            @Override
            public void presentBookSearchResponse(BookSearchOutputData outputData) {
                assertTrue(!outputData.getErrorMessage().equals(null));
            }
        };
        BookSearchInteractor interactor = new BookSearchInteractor(userRepo, failurePresenter);
        interactor.execute(inputData);
    }
}
