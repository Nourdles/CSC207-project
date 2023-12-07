package use_case.create_listing;

import data_access.InMemoryListingsDAO;
import entity.Listing;
import entity.ListingFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CreateListingInteractorTest {

    @Test
    void execute() throws IOException {
        CreateListingDataAccessInterface createListingDataAccessInterface = new InMemoryListingsDAO();

        File file = new File("demo.txt");
        CreateListingInputData createListingInputData = new CreateListingInputData("123", "Dracula",
                "user", 50.5, "Excellent", file);
        Listing listing = new ListingFactory().create("123", "Dracula",
                "user", 50.5, "Excellent", file, LocalDateTime.now());
        CreateListingOutputBoundary createListingOutputBoundary = new CreateListingOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertNotNull(listing.getCreationTime());
                assertTrue(createListingDataAccessInterface.existsById(listing.getListingId()));
            }
        };
        CreateListingInputBoundary createListingInputBoundary = new CreateListingInteractor(
                createListingDataAccessInterface, createListingOutputBoundary, new ListingFactory());
        createListingInputBoundary.execute(createListingInputData);
    }
}