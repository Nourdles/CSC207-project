package use_case.delete_listing;
import entity.Listing;
import entity.ListingFactory;
import use_case.create_listing.CreateListingDataAccessInterface;


public class DeleteListingInteractor implements DeleteListingInputBoundary {
    final DeleteListingDataAccessInterface listingDataAccessObject;
    final DeleteListingOutputBoundary deletePresenter;
    final ListingFactory listingFactory;
    public DeleteListingInteractor(DeleteListingDataAccessInterface listingDataAccessObject, DeleteListingOutputBoundary deletePresenter,
                                   ListingFactory listingFactory){
        this.listingDataAccessObject = listingDataAccessObject;
        this.deletePresenter = deletePresenter;
        this.listingFactory = listingFactory;
    }

    @Override
    public void execute(DeleteListingInputData deleteListingInputData) {
        DeleteListingOutputData deleteListingOutputData = new DeleteListingOutputData(listingDataAccessObject.delete(deleteListingInputData.getListingId()));
        deletePresenter.prepareDeleteView(deleteListingOutputData);

    }
}
