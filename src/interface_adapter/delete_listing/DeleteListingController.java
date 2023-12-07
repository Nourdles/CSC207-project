package interface_adapter.delete_listing;

import use_case.delete_listing.DeleteListingInputBoundary;
import use_case.delete_listing.DeleteListingInputData;

public class DeleteListingController {
    final DeleteListingInputBoundary deleteListingUseCaseInteractor;

    /**
     * Create a new Delete Listings Controller with the given parameter
     * @param deleteListingUseCaseInteractor Interactor for the Delete Listings use case
     */
    public DeleteListingController(DeleteListingInputBoundary deleteListingUseCaseInteractor){
        this.deleteListingUseCaseInteractor = deleteListingUseCaseInteractor;
    }

    /**
     * Create a new Input Data for the Delete Listing use case and call the execute method of the Interactor
     * @param listingId String representing the ID of a Listing
     */
    public void execute(String listingId){
        DeleteListingInputData deleteListingInputData = new DeleteListingInputData(listingId);
        deleteListingUseCaseInteractor.execute(deleteListingInputData);
    }
}
