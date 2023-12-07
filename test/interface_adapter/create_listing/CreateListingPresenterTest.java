package interface_adapter.create_listing;

import interface_adapter.ViewManagerModel;
import interface_adapter.book_info.BookInfoState;
import interface_adapter.book_info.BookInfoViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateListingPresenterTest {
    private final ViewManagerModel model = new ViewManagerModel();
    private final CreateListingViewModel listingViewModel = new CreateListingViewModel();
    private final BookInfoViewModel infoViewModel = new BookInfoViewModel();
    private CreateListingPresenter presenter = new CreateListingPresenter(model, listingViewModel, infoViewModel);

    @Test
    void prepareSuccessView() {
        presenter.prepareSuccessView();
        BookInfoState currentState = infoViewModel.getState();
        assertEquals("book info", model.getActiveView());
        assertEquals(currentState, infoViewModel.getState());
    }
}