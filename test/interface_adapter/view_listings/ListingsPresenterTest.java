package interface_adapter.view_listings;

import entity.Listing;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.view_listings.ListingsOutputData;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListingsPresenterTest {
    private final ListingsViewModel listingsViewModel = new ListingsViewModel();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private ListingsPresenter presenter = new ListingsPresenter(viewManagerModel, listingsViewModel);

    @Test
    void prepareListingsView() {
        ArrayList<Listing> listings = new ArrayList<>();
        File file = new File("demo.txt");
        Listing listing = new Listing("123", "Dracula", "user", 50.5, "Excellent", file,
                LocalDateTime.MAX);
        listings.add(listing);
        ListingsOutputData outputData = new ListingsOutputData(listings);
        presenter.prepareListingsView(outputData);
        ListingsState state = listingsViewModel.getState();

        assertEquals("listings", viewManagerModel.getActiveView());
        assertEquals(state, listingsViewModel.getState());
    }
}