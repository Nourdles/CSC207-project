package interface_adapter.create_listing;

import interface_adapter.ViewManagerModel;
import use_case.create_listing.CreateListingOutputBoundary;

public class CreateListingPresenter implements CreateListingOutputBoundary {

    /**
     * A Presenter to manage switching between create listing views.
     */
    private final ViewManagerModel viewManagerModel;
    private final CreateListingViewModel createListingViewModel;

    public CreateListingPresenter(ViewManagerModel viewManagerModel,
                                  CreateListingViewModel createListingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createListingViewModel = createListingViewModel;
    }

    @Override
    public void prepareSuccessView() {

    }
}
