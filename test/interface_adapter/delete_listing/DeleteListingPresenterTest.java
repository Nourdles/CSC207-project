package interface_adapter.delete_listing;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.delete_listing.DeleteListingOutputData;

import static org.junit.jupiter.api.Assertions.*;

class DeleteListingPresenterTest {
    private final DeleteListingViewModel listingViewModel = new DeleteListingViewModel();
    private final ViewManagerModel model = new ViewManagerModel();
    private DeleteListingPresenter presenter = new DeleteListingPresenter(model, listingViewModel);

    @Test
    void prepareDeleteView() {
        DeleteListingOutputData outputData = new DeleteListingOutputData("listingID");
        presenter.prepareDeleteView(outputData);
        DeleteListingState state = listingViewModel.getState();
        assertEquals(state, listingViewModel.getState());
        assertEquals("delete listing", model.getActiveView());
        assertEquals("listingID", state.getListing());
    }
}