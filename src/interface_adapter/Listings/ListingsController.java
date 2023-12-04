package interface_adapter.Listings;

import use_case.listings.ListingsInputBoundary;
import use_case.listings.ListingsInputData;

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
