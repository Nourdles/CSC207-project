package interface_adapter.Listings;

import java.util.List;

public class ListingsState {
    private List<String> listings;
    public ListingsState(ListingsState copy){
        listings = copy.listings;
    }
    public ListingsState(){}
    public List<String> getListings(){
        //String listing = String.join("\n", listings);
        return listings;
    }

    public void setListings(List<String> listings){
        this.listings = listings;
    }

    public String getListingsString(){
        String listing = String.join("\n", listings);
        return listing;
    }
}
