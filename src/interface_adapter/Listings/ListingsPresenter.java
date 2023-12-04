package interface_adapter.Listings;

import interface_adapter.ViewManagerModel;
import use_case.listings.ListingsOutputBoundary;
import use_case.listings.ListingsOutputData;

public class ListingsPresenter implements ListingsOutputBoundary {
    private final ListingsViewModel listingsViewModel;
    private final ViewManagerModel viewManagerModel;
    public ListingsPresenter(ViewManagerModel viewManagerModel, ListingsViewModel listingsViewModel){
        this.viewManagerModel = viewManagerModel;
        this.listingsViewModel = listingsViewModel;
    }
    @Override
    public void prepareListingsView(ListingsOutputData userListings) {
        ListingsState listingsState = listingsViewModel.getState();
        listingsState.setListings(userListings.getListings());
        this.listingsViewModel.setState(listingsState);
        viewManagerModel.setActiveView(listingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
