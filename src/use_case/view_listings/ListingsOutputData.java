package use_case.view_listings;

import entity.Listing;

import java.util.List;

public class ListingsOutputData {
    private final List<Listing> listings;

    /**
     * Create a new Listings Output Data with the given parameter
     * @param listings List of Listings
     */
    public ListingsOutputData(List listings){
        this.listings = listings;
    }

    /**
     * Returns the listings in the Output Data
     * @return List of Listings that represents all the listings in the Output Data
     */
    public List getListings(){ return listings;}
}
