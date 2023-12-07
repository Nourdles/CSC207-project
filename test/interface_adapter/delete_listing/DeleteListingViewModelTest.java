package interface_adapter.delete_listing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteListingViewModelTest {
    private DeleteListingViewModel model = new DeleteListingViewModel();

    @Test
    void setState() {
        DeleteListingState state = new DeleteListingState();
        model.setState(state);
        assertEquals(state, model.getState());
    }

    @Test
    void getState() {
        DeleteListingState state = new DeleteListingState();
        model.setState(state);
        assertEquals(state, model.getState());
    }
}