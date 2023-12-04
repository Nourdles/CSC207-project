package use_case.listings;

import entity.Listing;

import java.util.List;

public interface ListingsDataAccessInterface {
    List<Listing> getUserListings(String username);
}