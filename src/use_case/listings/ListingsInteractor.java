package use_case.listings;

import interface_adapter.Listings.ListingsPresenter;

public class ListingsInteractor implements ListingsInputBoundary {
    final ListingsDataAccessInterface listingsDataAccessInterface;
    final ListingsOutputBoundary listingsPresenter;

    public ListingsInteractor(ListingsDataAccessInterface listingsDataAccessInterface, ListingsOutputBoundary listingsPresenter){
        this.listingsDataAccessInterface = listingsDataAccessInterface;
        this.listingsPresenter = listingsPresenter;
    }
    @Override
    public void execute(ListingsInputData listingsInputData){
        ListingsOutputData listingsOutputData = new ListingsOutputData(listingsDataAccessInterface.getUserListings(listingsInputData.getUsername()));
        listingsPresenter.prepareListingsView(listingsOutputData);
    }
}
