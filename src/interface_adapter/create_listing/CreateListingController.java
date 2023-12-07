package interface_adapter.create_listing;

import use_case.create_listing.CreateListingInputBoundary;
import use_case.create_listing.CreateListingInputData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateListingController {

    final CreateListingInputBoundary createListingInteractor;

    /**
     * Create a new Create Listing Controller with the given parameters
     * @param createListingInteractor Create Listing Interactor
     */
    public CreateListingController(CreateListingInputBoundary createListingInteractor) {
        this.createListingInteractor = createListingInteractor;
    }

    /**
     * Create a new Input Data for the Create Listing use case with the given parameters and call the execute method
     * of the Interactor
     * @param title title of the Book we want to create a Listing for
     * @param ISBN ISBN of the Book we want to create a Listing for
     * @param seller username of the User creating the Listing
     * @param listingPrice price of the Listing
     * @param condition condition of the Listing
     * @param bookPhoto photo showing the book listed
     * @throws IOException
     */
    public void execute(String title, String ISBN, String seller, double listingPrice, String condition, File bookPhoto, LocalDateTime ltd) throws IOException {
        CreateListingInputData createListingInputData = new CreateListingInputData(
                title, ISBN, seller, listingPrice, condition, bookPhoto);
        createListingInteractor.execute(createListingInputData);
    }
}
