package use_case.create_listing;

import entity.Listing;

import java.io.IOException;
import java.util.List;

public interface CreateListingDataAccessInterface {
    void save(Listing listing) throws IOException;
    boolean existsById(String listingId);


}
