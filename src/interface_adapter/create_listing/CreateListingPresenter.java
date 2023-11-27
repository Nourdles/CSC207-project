package interface_adapter.create_listing;

import interface_adapter.ViewManagerModel;
import use_case.create_listing.CreateListingOutputBoundary;

public class CreateListingPresenter implements CreateListingOutputBoundary {
    public CreateListingPresenter(ViewManagerModel viewManagerModel, CreateListingViewModel createListingViewModel) {
    }

    @Override
    public void prepareSuccessView() {

    }
}
