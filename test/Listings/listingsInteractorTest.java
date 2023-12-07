package Listings;

import data_access.InMemoryListingsDAO;
import entity.Listing;
import entity.ListingFactory;
import org.junit.jupiter.api.Test;
import use_case.view_listings.*;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class listingsInteractorTest {
    @Test
    void successTest(){
        ListingsInputData inputData = new ListingsInputData("Unu");
        File photo = new File("bookPhoto.png");
        Listing listing1 = new ListingFactory().create("Dracula","123", "Unu", 23, "Good", photo, LocalDateTime.now());
        ListingsDataAccessInterface userRepo = new InMemoryListingsDAO();
        ((InMemoryListingsDAO) userRepo).addListing(listing1);

        ListingsOutputBoundary successPresnter = new ListingsOutputBoundary() {
            @Override
            public void prepareListingsView(ListingsOutputData listingsOutputData) {
                assertNull(listingsOutputData.getListings());
            }
        };
        ListingsInputBoundary interactor = new ListingsInteractor(userRepo, successPresnter);
        interactor.execute(inputData);

    }
}
