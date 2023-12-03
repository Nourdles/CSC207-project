package use_case.delete_listing;
import entity.Listing;

import java.io.IOException;

public interface DeleteListingDataAccessInterface {
    String delete(String listingId) throws IOException;

}
