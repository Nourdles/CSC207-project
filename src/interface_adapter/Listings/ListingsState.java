package interface_adapter.Listings;

import entity.Listing;

import java.util.ArrayList;
import java.util.List;

public class ListingsState {
    private List<Listing> listings = new ArrayList<>();
    private String username = "";
    public ListingsState(ListingsState copy){
        listings = copy.listings;
        username = copy.username;
    }
    public ListingsState(){}
    public List<String> getListingsString(){
        //String listing = String.join("\n", listings);
        List<String> listingsString = new ArrayList<>();
        for (Listing listing: listings){
            listingsString.add(listing.getListingId());
        }
        return listingsString;
    }
    public List<Listing> getListings(){
        return listings;
    }


    public void setListings(List<Listing> listings){
        this.listings = listings;
    }

//    public String getListingsString(){
//        String listing = String.join("\n", listings.g);
//        return listing;
//    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}
