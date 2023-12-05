package use_case.listings;

import entity.Listing;

import java.util.List;

public class ListingsOutputData {
    private final List<Listing> listings;
    public ListingsOutputData(List<Listing> listings){
        this.listings = listings;
    }
    public List<Listing> getListings(){ return listings;}
}
