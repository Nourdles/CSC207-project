package use_case.create_listing;

import entity.Listing;
import entity.ListingFactory;

import java.io.IOException;

public class CreateListingInteractor implements CreateListingInputBoundary{

    final CreateListingDataAccessInterface listingDataAccessObject;
    final CreateListingOutputBoundary listingPresenter;
    final ListingFactory listingFactory;

    /**
     * Creates a new Create Listing Interactor with the given parameters
     * @param listingDataAccessInterface Listing Data Access Interface
     * @param createListingOutputBoundary Create Listing Output Boundary
     * @param listingFactory Listing Factory
     */
    public CreateListingInteractor(CreateListingDataAccessInterface listingDataAccessInterface,
                                   CreateListingOutputBoundary createListingOutputBoundary,
                                   ListingFactory listingFactory){
        this.listingDataAccessObject = listingDataAccessInterface;
        this.listingPresenter = createListingOutputBoundary;
        this.listingFactory = listingFactory;
    }

    /**
     * Create a new Listing through the Factory's create() method, then save it through the DAO and call the Presenter's
     * prepareSuccessView method.
     * @param createListingInputData Create Listing Input Data
     * @throws IOException
     */
    @Override
    public void execute(CreateListingInputData createListingInputData) throws IOException {
        Listing listing = listingFactory.create(createListingInputData.getTitle(), createListingInputData.getBookISBN(), createListingInputData.getSeller(),
                createListingInputData.getListingPrice(), createListingInputData.getCondition(),
                createListingInputData.getBookPhoto(), createListingInputData.getCreationTime());
        listingDataAccessObject.save(listing);
        listingPresenter.prepareSuccessView();
    }
}
