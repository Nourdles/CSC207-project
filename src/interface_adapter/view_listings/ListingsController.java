package interface_adapter.view_listings;

import use_case.view_listings.ListingsInputBoundary;
import use_case.view_listings.ListingsInputData;

public class ListingsController {
    final ListingsInputBoundary listingsInputBoundary;
    public ListingsController(ListingsInputBoundary listingsInputBoundary){
        this.listingsInputBoundary = listingsInputBoundary;
    }
    public void execute(String username){
        ListingsInputData listingsInputData = new ListingsInputData(username);
        listingsInputBoundary.execute(listingsInputData);
    }
}
