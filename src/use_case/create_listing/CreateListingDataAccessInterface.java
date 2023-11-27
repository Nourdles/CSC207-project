package use_case.create_listing;

import entity.Listing;

public interface CreateListingDataAccessInterface {
    void save(Listing listing);
    boolean existsById(String listingId);
}
