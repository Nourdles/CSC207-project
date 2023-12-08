package use_case.view_listings;

import data_access.InMemoryListingsDAO;
import entity.Listing;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListingsInteractorTest {
    private Listing listing;

    @Test
    void execute() {
        ArrayList<Listing> listings = new ArrayList<>();
        File file = new File("demo.txt");
        listing = new Listing("123", "Dracula", "user", 50.5, "Excellent", file,
                LocalDateTime.MAX);
        listings.add(listing);

        ListingsDataAccessInterface listingsDataAccessInterface = new InMemoryListingsDAO();
        listingsDataAccessInterface.save(listing);

        ListingsInputData listingsInputData = new ListingsInputData("user");
        ListingsOutputBoundary listingsOutputBoundary = new ListingsOutputBoundary() {
            @Override
            public void prepareListingsView(ListingsOutputData listingsOutputData) {
                assertEquals(listings, listingsOutputData.getListings());
            }
        };
        ListingsInputBoundary listingsInputBoundary = new ListingsInteractor(listingsDataAccessInterface,
                listingsOutputBoundary);
        listingsInputBoundary.execute(listingsInputData);
    }
}