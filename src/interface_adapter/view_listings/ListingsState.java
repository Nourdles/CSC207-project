package interface_adapter.view_listings;

import entity.Listing;

import java.util.ArrayList;
import java.util.List;

public class ListingsState {
    private List<Listing> listings = new ArrayList<>();
    private String username = "";

    /**
     * Creates a new Listings State
     */
    public ListingsState(){}

    /**
     * Returns a List of all the listings of the currently logged-in User in the State
     * @return List of Strings that represent all the listings of the currently logged-in User in the State
     */
    public List<String> getListingsString(){
        List<String> listingsString = new ArrayList<>();
        for (Listing listing: listings){
            listingsString.add(listing.getListingId());
        }
        return listingsString;
    }

    /**
     * Returns the List of all the Listings of the currently logged-in User
     * @return List of Listings that represent all the Listings of the currently logged-in User
     */
    public List<Listing> getListings(){
        return listings;
    }

    /**
     * Sets the List of all the Listings of the currently logged-in User
     * @param listings List of Listings that represent all the Listings of the currently logged-in User
     */
    public void setListings(List<Listing> listings){
        this.listings = listings;
    }

    /**
     * Sets the username of the currently logged-in User
     * @param username String that represents the username of the currently logged-in User
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Returns the username of the currently logged-in User
     * @return String that represents the username of the currently logged-in User
     */
    public String getUsername(){
        return username;
    }
}
