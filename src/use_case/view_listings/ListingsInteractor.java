package use_case.view_listings;

public class ListingsInteractor implements ListingsInputBoundary {
    final ListingsDataAccessInterface listingsDataAccessInterface;
    final ListingsOutputBoundary listingsPresenter;

    /**
     * Creates a new Listings Interactor with the given parameters
     * @param listingsDataAccessInterface Listings Data Access Interface
     * @param listingsPresenter Listings Presenter
     */
    public ListingsInteractor(ListingsDataAccessInterface listingsDataAccessInterface, ListingsOutputBoundary listingsPresenter){
        this.listingsDataAccessInterface = listingsDataAccessInterface;
        this.listingsPresenter = listingsPresenter;
    }

    /**
     * Creates a new Output Data for the View Listings use case using the getUserListings method of the Data Access
     * Interface and calls the prepareListingsView of the Presenter
     * @param listingsInputData Listings Input Data
     */
    @Override
    public void execute(ListingsInputData listingsInputData){
        ListingsOutputData listingsOutputData = new ListingsOutputData(listingsDataAccessInterface.getUserListings(listingsInputData.getUsername()));
        listingsPresenter.prepareListingsView(listingsOutputData);
    }
}
