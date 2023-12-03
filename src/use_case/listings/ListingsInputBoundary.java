package use_case.listings;

import use_case.delete_listing.DeleteListingInputData;

public interface ListingsInputBoundary {
    void execute(ListingsInputData listingsInputData);
}
