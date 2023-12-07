package interface_adapter.create_listing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateListingViewModelTest {
    private CreateListingViewModel viewModel = new CreateListingViewModel();

    @Test
    void getState() {
        CreateListingState state = new CreateListingState();
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    @Test
    void setState() {
        CreateListingState state = new CreateListingState();
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }
}