package interface_adapter.create_listing;

import interface_adapter.ViewManagerModel;
import interface_adapter.book_info.*;
import use_case.create_listing.CreateListingOutputBoundary;

public class CreateListingPresenter implements CreateListingOutputBoundary {

    /**
     * A Presenter to manage switching between create listing views.
     */
    private final ViewManagerModel viewManagerModel;
    private final CreateListingViewModel createListingViewModel;
    private final BookInfoViewModel bookInfoViewModel;

    public CreateListingPresenter(ViewManagerModel viewManagerModel,
                                  CreateListingViewModel createListingViewModel, BookInfoViewModel bookInfoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createListingViewModel = createListingViewModel;
        this.bookInfoViewModel = bookInfoViewModel;
    }

    @Override
    public void prepareSuccessView() {
        BookInfoState currentState = bookInfoViewModel.getState();
        bookInfoViewModel.setState(currentState);
        bookInfoViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(bookInfoViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();


    }
}
