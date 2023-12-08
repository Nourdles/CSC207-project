package interface_adapter.view_listings;

import interface_adapter.ViewManagerModel;
import use_case.view_listings.ListingsOutputBoundary;
import use_case.view_listings.ListingsOutputData;

public class ListingsPresenter implements ListingsOutputBoundary {
    private final ListingsViewModel listingsViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Creates a new Listings Presenter with the given parameters
     * @param viewManagerModel View Manager Model
     * @param listingsViewModel Listings View Model
     */
    public ListingsPresenter(ViewManagerModel viewManagerModel, ListingsViewModel listingsViewModel){
        this.viewManagerModel = viewManagerModel;
        this.listingsViewModel = listingsViewModel;
    }

    /**
     * Creates a new State for the View Listings use case and sets its Listings to the ones in the given Output Data,
     * then changes the current Profile View to the Listings View
     * @param userListings Listings Output Data
     */
    @Override
    public void prepareListingsView(ListingsOutputData userListings) {
        ListingsState listingsState = listingsViewModel.getState();
        listingsState.setListings(userListings.getListings());
        this.listingsViewModel.setState(listingsState);
        viewManagerModel.setActiveView(listingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
