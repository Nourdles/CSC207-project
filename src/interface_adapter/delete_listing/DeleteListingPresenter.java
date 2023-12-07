package interface_adapter.delete_listing;
import interface_adapter.ViewManagerModel;
import use_case.delete_listing.DeleteListingOutputBoundary;
import use_case.delete_listing.DeleteListingOutputData;

public class DeleteListingPresenter implements DeleteListingOutputBoundary {
    private final DeleteListingViewModel deleteListingViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Create a new Delete Listings Presenter with the given parameters
     * @param viewManagerModel View Manager Model
     * @param deleteListingViewModel Delete Listing View Model
     */
    public DeleteListingPresenter(ViewManagerModel viewManagerModel,
                                  DeleteListingViewModel deleteListingViewModel){
        this.viewManagerModel = viewManagerModel;
        this.deleteListingViewModel = deleteListingViewModel;
    }

    /**
     * Assuming no errors were encountered in the rest of the use case, switches the view to the Delete Listing View
     * @param deletedListing Delete Listing Output Data
     */
    @Override
    public void prepareDeleteView(DeleteListingOutputData deletedListing) {
        DeleteListingState deleteListingState = deleteListingViewModel.getState();
        deleteListingState.setListing(deletedListing.getDeleted_listing());
        this.deleteListingViewModel.setState(deleteListingState);
        viewManagerModel.setActiveView(deleteListingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
