package data_access;

import entity.Listing;
import entity.User;
import use_case.book_info.BookInfoDataAccessInterface;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.delete_listing.DeleteListingDataAccessInterface;
import use_case.view_listings.ListingsDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryListingsDAO implements ListingsDataAccessInterface, DeleteListingDataAccessInterface,
        BookInfoDataAccessInterface, CreateListingDataAccessInterface {
    private final Map<String, Listing> listings = new HashMap<>();

    /**
     * Saves the given Listing and its ID to the Map
     * @param listing Listing we want to save
     */
    @Override
    public void save(Listing listing){
        listings.put(listing.getListingId(), listing);
    }

    @Override
    public boolean existsById(String listingId) {
        for(String id : listings.keySet()){
            if(id.equals(listingId)){
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes the listing that has the given listing ID
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
     * Returns a list of the listings created by the User with the given username
     * @param username the username of the user of the listings
     * @return  a list of the users listing with this username
     */
    @Override
    public List<Listing> getUserListings(String username) {
        List<Listing> listingsList = new ArrayList<>();
        for (Listing listing : listings.values()){
            if (listing.getSeller().equals(username)){
                listingsList.add(listing);
            }
        }
        return listingsList;
    }

    /**
     * Adds a given Listing and its listing ID to our listings map
     * @param listing the Listing we want to add to the listings map
     */
    public void addListing(Listing listing){
        listings.put(listing.getListingId(), listing);
    }

    /**
     * Returns a list of listings for a specific Book, given that Book's ISBN
     * @param ISBN the ISBN of the Book we want to retrieve the listings of
     * @return a list of listings for a specific Book, given that Book's ISBN
     */
    @Override
    public List<Listing> getBookListings(String ISBN) {
        List<Listing> listingsList = new ArrayList<>();
        for (Listing listing : listings.values()){
            if (listing.getISBN().equals(ISBN)){
                listingsList.add(listing);
            }
        }
        return listingsList;
    }

    /**
     * Return the city of a specific User given their username
     * @param username the username of the User whose city we want
     * @return a string representing the city of a specific User given their username
     */
    @Override
    public String findCity(String username) {
        return null;
    }

    /**
     * Return the email of a specific User given their username
     * @param username the username of the User whose email we want
     * @return a string representing the email of a specific User given their username
     */
    @Override
    public String findEmail(String username) {
        return null;
    }

    /**
     * Return the phone number of a specific User given their username
     * @param username username of the user whose phone number we want
     * @return a string representing the phone number of a specific User given their username
     */
    @Override
    public String findPhoneNumber(String username) {
        return null;
    }

    @Override
    public void save(User user) {

    }
}
