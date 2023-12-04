package delete_listings;

import data_access.InMemoryListingsDAO;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.delete_listing.DeleteListingDataAccessInterface;
import use_case.delete_listing.DeleteListingInputData;
import use_case.delete_listing.DeleteListingOutputBoundary;
import use_case.delete_listing.DeleteListingOutputData;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import use_case.delete_listing.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteListingInteractorTest {
    @Test
    void successTest(){
        Book book = new Book( "Dracula", 1988, "Author", "SUmmary", "123", 0, "URL",
                "english", new ArrayList<>());
        CommonUser user = new CommonUserFactory().create("Unu", "Password123", LocalDateTime.now(),
                "unu@mail.com", "1234456", "toronto");
        File photo = new File("bookPhoto.png");
        Listing listing1 = new ListingFactory().create("Dracula","123", "Unu", 23, "Good", photo, LocalDateTime.now());
        DeleteListingInputData inputData = new DeleteListingInputData(listing1.getListingId());
        DeleteListingDataAccessInterface listingRepo = new InMemoryListingsDAO();
        ((InMemoryListingsDAO) listingRepo).addListing(listing1);
        DeleteListingOutputBoundary successPresenter = new DeleteListingOutputBoundary() {
            @Override
            public void prepareDeleteView(DeleteListingOutputData deletedListing) {
                assertEquals("123", deletedListing.getDeleted_listing());
            }
        };
        DeleteListingInputBoundary interactor = new DeleteListingInteractor(listingRepo, successPresenter);
        interactor.execute(inputData);
    }
}
