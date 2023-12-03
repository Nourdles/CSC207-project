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
    public List<Listing> getListings(){
        //String listing = String.join("\n", listings);
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
