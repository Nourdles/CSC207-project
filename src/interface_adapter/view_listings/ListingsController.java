package interface_adapter.view_listings;

import use_case.view_listings.ListingsInputBoundary;
import use_case.view_listings.ListingsInputData;

public class ListingsController {
    final ListingsInputBoundary listingsInputBoundary;

    /**
     * Creates a new Listings Controller with the given parameters
     * @param listingsInputBoundary Listings Input Boundary
     */
    public ListingsController(ListingsInputBoundary listingsInputBoundary){
        this.listingsInputBoundary = listingsInputBoundary;
    }

    /**
     * Creates a new Input Data for the View Listings use case with the given username, and call the execute method of
     * the Listings Interactor
     * @param username String that represents the username of the currently logged-in User
     */
    public void execute(String username){
        ListingsInputData listingsInputData = new ListingsInputData(username);
        listingsInputBoundary.execute(listingsInputData);
    }
}
