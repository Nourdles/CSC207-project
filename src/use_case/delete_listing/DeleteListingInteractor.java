package use_case.delete_listing;
import entity.Listing;
import entity.ListingFactory;
import use_case.create_listing.CreateListingDataAccessInterface;

import java.io.IOException;


public class DeleteListingInteractor implements DeleteListingInputBoundary {
    final DeleteListingDataAccessInterface listingDataAccessObject;
    final DeleteListingOutputBoundary deletePresenter;

    public DeleteListingInteractor(DeleteListingDataAccessInterface listingDataAccessObject, DeleteListingOutputBoundary deletePresenter
                                   ){
        this.listingDataAccessObject = listingDataAccessObject;
        this.deletePresenter = deletePresenter;

    }

    @Override
    public void execute(DeleteListingInputData deleteListingInputData) throws IOException {
        DeleteListingOutputData deleteListingOutputData = new DeleteListingOutputData(listingDataAccessObject.delete(deleteListingInputData.getListingId()));
        deletePresenter.prepareDeleteView(deleteListingOutputData);

    }
}
