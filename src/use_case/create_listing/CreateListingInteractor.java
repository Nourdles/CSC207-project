package use_case.create_listing;

import entity.ListingFactory;

public class CreateListingInteractor {

    final CreateListingDataAccessInterface createListingDataAccessObject;
    final CreateListingOutputBoundary createListingPresenter;
    final ListingFactory listingFactory;

    public CreateListingInteractor(CreateListingDataAccessInterface createListingDataAccessInterface,
                                   CreateListingOutputBoundary createListingOutputBoundary,
                                   ListingFactory listingFactory){
        this.createListingDataAccessObject = createListingDataAccessInterface;
        this.createListingPresenter = createListingOutputBoundary;
        this.listingFactory = listingFactory;
    }
    public void execute(CreateListingInputData createListingInputData) {

    }
}
