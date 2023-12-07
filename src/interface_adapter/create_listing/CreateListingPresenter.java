package interface_adapter.create_listing;

import interface_adapter.ViewManagerModel;
import interface_adapter.book_info.*;
import use_case.create_listing.CreateListingOutputBoundary;

public class CreateListingPresenter implements CreateListingOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CreateListingViewModel createListingViewModel;
    private final BookInfoViewModel bookInfoViewModel;

    /**
     * Create a new Create Listing Presenter with the given parameters
     * @param viewManagerModel View Manager Model
     * @param createListingViewModel Create Listing View Model
     * @param bookInfoViewModel Book Info View Model
     */
    public CreateListingPresenter(ViewManagerModel viewManagerModel,
                                  CreateListingViewModel createListingViewModel, BookInfoViewModel bookInfoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createListingViewModel = createListingViewModel;
        this.bookInfoViewModel = bookInfoViewModel;
    }

    /**
     * Assuming no errors were encountered in the use case, changes the view to the Book Info View
     */
    @Override
    public void prepareSuccessView() {
        BookInfoState currentState = bookInfoViewModel.getState();
        bookInfoViewModel.setState(currentState);
        bookInfoViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(bookInfoViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
