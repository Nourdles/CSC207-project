package use_case.delete_listing;

public class DeleteListingInteractor implements DeleteListingInputBoundary {
    final DeleteListingDataAccessInterface listingDataAccessObject;
    final DeleteListingOutputBoundary deletePresenter;

    /**
     * Create a new Delete Listing Interactor with the given parameters
     * @param listingDataAccessObject Delete Listing Data Access Interface
     * @param deletePresenter Delete Listing Output Boundary
     */
    public DeleteListingInteractor(DeleteListingDataAccessInterface listingDataAccessObject, DeleteListingOutputBoundary deletePresenter
                                   ){
        this.listingDataAccessObject = listingDataAccessObject;
        this.deletePresenter = deletePresenter;
    }

    /**
     * Creates a new Output Data for the Delete Listing use case by calling the DAO's delete method, then calls the
     * Presenter's prepareDeleteView()
     * @param deleteListingInputData Delete Listing Input Data
     */
    @Override
    public void execute(DeleteListingInputData deleteListingInputData) {
        DeleteListingOutputData deleteListingOutputData = new DeleteListingOutputData(listingDataAccessObject.delete(deleteListingInputData.getListingId()));
        deletePresenter.prepareDeleteView(deleteListingOutputData);

    }
}
