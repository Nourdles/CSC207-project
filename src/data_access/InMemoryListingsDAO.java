package data_access;

import entity.CommonUser;
import entity.Listing;
import use_case.book_info.BookInfoDataAccessInterface;
import use_case.delete_listing.DeleteListingDataAccessInterface;
import use_case.listings.ListingsDataAccessInterface;

import javax.management.StringValueExp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryListingsDAO implements ListingsDataAccessInterface, DeleteListingDataAccessInterface,
        BookInfoDataAccessInterface {
    private final Map<String, Listing> listings = new HashMap<>();

    /**
     *
     * @param listingId the listings' IDs
     * @return the deleted listing's ID
     */
    @Override
    public String delete(String listingId) {
        for(String id: listings.keySet()){
            if (id.equals(listingId)){
                listings.remove(listingId);
            }
        }

        return listingId;
    }

    /**
     *
     * @param username the username of the user of the listings
     * @return  a list of the users listing with this username
     */
    @Override
    public List<Listing> getUserListings(String username) {
        return null;
    }
    public void addListing(Listing listing){
        listings.put(listing.getListingId(), listing);
    }

    @Override
    public List<Listing> getBookListings(String ISBN) {
        return null;
    }

    @Override
    public CommonUser findUserByUsername(String username) {
        return null;
    }
}
