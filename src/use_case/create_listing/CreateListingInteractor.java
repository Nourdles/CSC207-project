package use_case.create_listing;

import data_access.FileListingDataAccessObject;
import entity.Listing;
import entity.ListingFactory;

import java.io.IOException;
import java.time.LocalDateTime;

public class CreateListingInteractor implements CreateListingInputBoundary{

    final CreateListingDataAccessInterface listingDataAccessObject;
    final CreateListingOutputBoundary listingPresenter;
    final ListingFactory listingFactory;

    public CreateListingInteractor(CreateListingDataAccessInterface listingDataAccessInterface,
                                   CreateListingOutputBoundary createListingOutputBoundary,
                                   ListingFactory listingFactory){
        this.listingDataAccessObject = listingDataAccessInterface;
        this.listingPresenter = createListingOutputBoundary;
        this.listingFactory = listingFactory;
    }
    @Override
    public void execute(CreateListingInputData createListingInputData) throws IOException {
        Listing listing = listingFactory.create(createListingInputData.getBook(), createListingInputData.getSeller(),
                createListingInputData.getListingPrice(), createListingInputData.getCondition(),
                createListingInputData.getBookPhoto(), createListingInputData.getCreationTime());
        listingDataAccessObject.save(listing);
        listingPresenter.prepareSuccessView();
    }
}
