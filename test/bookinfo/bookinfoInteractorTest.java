package bookinfo;

import data_access.InMemoryListingsDAO;
import entity.Listing;
import entity.ListingFactory;
import org.junit.jupiter.api.Test;
import use_case.book_info.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class bookinfoInteractorTest {

    @Test
    void failTest(){
        BookInfoInputData inputData = new BookInfoInputData("Dracula", 1998, "Author", "123",
                "123", "english", new ArrayList<>() );
        File photo = new File("bookPhoto.png");
        Listing listing1 = new ListingFactory().create("Dracula","123", "Unu", 23, "Good", photo, LocalDateTime.now());

        BookInfoDataAccessInterface userRepo = new InMemoryListingsDAO();
        ((InMemoryListingsDAO) userRepo).addListing(listing1);
        BookInfoOutputBoundary failurePresenter = new BookInfoOutputBoundary() {
            @Override
            public void displayBookInfo(BookInfoOutputData outputData) {

                assertEquals("Cannot invoke \"java.util.List.iterator()\" because \"listings\" is null", outputData.getErrorMessage());
            }
        };
        BookInfoInputBoundary interactor = new BookInfoInteractor(failurePresenter, userRepo);
        interactor.showBookDetails(inputData);
    }
}
