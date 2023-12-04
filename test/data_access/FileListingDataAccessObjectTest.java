package data_access;

import app.CreateListingUseCaseFactory;
import entity.*;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.create_listing.CreateListingInteractor;
import use_case.create_listing.CreateListingOutputBoundary;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the csv file that stores listing info and the directory images system for storing book photos.
 * Note: Listings' data cannot persist.
 */
class FileListingDataAccessObjectTest {
    private FileListingDataAccessObject fileListingDAO;
    private String sellerName;
    private Listing listing;
    private String ISBN;
    private Map<String, Listing> expectedMap = new HashMap<>();
    private Map<String, Listing> actualMap = new HashMap<>();
    private File listingInfoCSV = new File("listingInfo.csv");
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        ListingFactory listingFactory = new ListingFactory();
        String photoPath = "src/default.png";
        String listingsPath = "listingInfo.csv";
        String bookTitle = "History";
        sellerName = "Bob Jones";
        ISBN = "1234567898765";
        File bookPhoto = new File(photoPath);
        LocalDateTime bookLDT = LocalDateTime.now();
        listing = listingFactory.create(bookTitle, ISBN, sellerName, 50, "Excellent", bookPhoto, bookLDT);
        fileListingDAO = new FileListingDataAccessObject(listingsPath, listingFactory);
        fileListingDAO.save(listing);
    }
    @org.junit.jupiter.api.BeforeEach
    void clear() {
    }
    @org.junit.jupiter.api.Test
    void save() {
        String listingId = ISBN;
        expectedMap.put(listingId, listing);
        actualMap = fileListingDAO.getListingInfo();
        assertEquals(actualMap, expectedMap);
    }

    @org.junit.jupiter.api.Test
    void existsById() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void getUserListings() {
    }
}