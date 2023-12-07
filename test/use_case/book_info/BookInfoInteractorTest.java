package use_case.book_info;

import data_access.InMemoryListingsDAO;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.book_info.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BookInfoInteractorTest {

    @Test
    void successTest () {
        BookInfoInputData inputData = new BookInfoInputData("Dracula", 1998, "Author", "123",
                "123", "english", new ArrayList<>());
        File photo = new File("bookPhoto.png");
        BookInfoDataAccessInterface userRepo = new InMemoryListingsDAO();
        Listing listing1 = new ListingFactory().create("Dracula","123", "Unu", 23,
                "Good", photo, LocalDateTime.now());
        ((InMemoryListingsDAO) userRepo).save(listing1);

        BookInfoDataAccessInterface users = new InMemoryUserDataAccessObject();
        CommonUser unu = (CommonUser) new CommonUserFactory().create("Unu", "123", LocalDateTime.MAX,
                "unu@mail.com", "1234", "Toronto");
        users.save(unu);
        
        BookInfoOutputBoundary successPresenter = new BookInfoOutputBoundary() {
            @Override
            public void displayBookInfo(BookInfoOutputData outputData) {
                assertTrue(outputData.isSuccess());
                ArrayList<ArrayList> listings = new ArrayList<>();
                ArrayList<String> listing = new ArrayList<>();
                listing.add("Unu");
                listing.add("23.0");
                listing.add("Good");
                listing.add(null);
                listing.add(null);
                listing.add(null);
                listings.add(listing);
                
                assertEquals(listings, outputData.getListingsDetails());
                assertEquals("Dracula", outputData.getTitle());
                assertEquals("Author", outputData.getAuthor());
                assertEquals(1998, outputData.getYear());
                assertEquals("123", outputData.getISBN());
                assertEquals("english", outputData.getLanguage());
                assertEquals("123", outputData.getCoverURL());
                assertEquals(new ArrayList<>(), outputData.getSubjects());
            }
        };
        BookInfoInputBoundary interactor = new BookInfoInteractor(successPresenter, userRepo);
        interactor.showBookDetails(inputData);
    }
}
