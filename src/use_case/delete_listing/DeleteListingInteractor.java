package use_case.delete_listing;


public class DeleteListingInteractor implements DeleteListingInputBoundary {
    final DeleteListingDataAccessInterface listingDataAccessObject;
    final DeleteListingOutputBoundary deletePresenter;

    public DeleteListingInteractor(DeleteListingDataAccessInterface listingDataAccessObject, DeleteListingOutputBoundary deletePresenter
                                   ){
        this.listingDataAccessObject = listingDataAccessObject;
        this.deletePresenter = deletePresenter;

    }

    @Override
    public void execute(DeleteListingInputData deleteListingInputData) {
        DeleteListingOutputData deleteListingOutputData = new DeleteListingOutputData(listingDataAccessObject.delete(deleteListingInputData.getListingId()));
        deletePresenter.prepareDeleteView(deleteListingOutputData);

    }
}
