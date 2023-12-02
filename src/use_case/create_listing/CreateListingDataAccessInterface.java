package use_case.create_listing;

import entity.Listing;

import java.io.IOException;

public interface CreateListingDataAccessInterface {
    void save(Listing listing) throws IOException;
    boolean existsById(String listingId);
}
