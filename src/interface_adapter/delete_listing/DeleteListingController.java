package interface_adapter.delete_listing;

import use_case.delete_listing.DeleteListingInputBoundary;
import use_case.delete_listing.DeleteListingInputData;

import java.io.IOException;

public class DeleteListingController {
    final DeleteListingInputBoundary deleteListingUseCaseInteractor;
    public DeleteListingController(DeleteListingInputBoundary deleteListingUseCaseInteractor){
        this.deleteListingUseCaseInteractor = deleteListingUseCaseInteractor;
    }
    public void execute(String listingId) throws IOException {
        DeleteListingInputData deleteListingInputData = new DeleteListingInputData(listingId);
        deleteListingUseCaseInteractor.execute(deleteListingInputData);
    }
}
