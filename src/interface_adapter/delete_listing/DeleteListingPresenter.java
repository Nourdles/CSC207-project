package interface_adapter.delete_listing;
import interface_adapter.delete_listing.DeleteListingState;
import interface_adapter.ViewManagerModel;
import use_case.delete_listing.DeleteListingOutputBoundary;
import use_case.delete_listing.DeleteListingOutputData;

public class DeleteListingPresenter implements DeleteListingOutputBoundary {
    private final DeleteListingViewModel deleteListingViewModel;
    private final ViewManagerModel viewManagerModel;
    public DeleteListingPresenter(ViewManagerModel viewManagerModel,
                                  DeleteListingViewModel deleteListingViewModel){
        this.viewManagerModel = viewManagerModel;
        this.deleteListingViewModel = deleteListingViewModel;
    }
    @Override
    public void prepareDeleteView(DeleteListingOutputData deletedListing) {
        DeleteListingState deleteListingState = deleteListingViewModel.getState();
        deleteListingState.setListing(deletedListing.getDeleted_listing());
        this.deleteListingViewModel.setState(deleteListingState);
        viewManagerModel.setActiveView(deleteListingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
