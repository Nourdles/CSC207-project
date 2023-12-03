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
    private Book book;
    private Listing listing;
    private Map<String, Listing> expectedMap = new HashMap<>();
    private Map<String, Listing> actualMap = new HashMap<>();
    private File listingInfoCSV = new File("listingInfo.csv");
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        ListingFactory listingFactory = new ListingFactory();
        CreateListingDataAccessInterface createListingDataAccessInterface;
        CreateListingOutputBoundary createListingOutputBoundary;
        // CreateListingInteractor createListingInteractor = new CreateListingInteractor(createListingDataAccessInterface,
        //        createListingOutputBoundary, listingFactory);
        String photoPath = "src/default.png";
        String listingsPath = "listingInfo.csv";
        book = new Book("History", 1994, "Dan Jones", "Lorem ipsum", "1234567891234",
                5, "");
        File bookPhoto = new File(photoPath);
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        LocalDateTime userLDT = LocalDateTime.now();
        CommonUser seller = commonUserFactory.create("abli2003", "Abc12345", userLDT,
                "albert3@gmail.com", "4164164120", "Toronto");

        LocalDateTime bookLDT = LocalDateTime.now();
        listing = listingFactory.create(book, seller, 50, "Excellent", bookPhoto, bookLDT);
        fileListingDAO = new FileListingDataAccessObject(listingsPath, listingFactory);
        fileListingDAO.save(listing);
    }
    @org.junit.jupiter.api.BeforeEach
    void clear() {
    }
    @org.junit.jupiter.api.Test
    void save() {
        String listingIdAppender = String.valueOf(book.getEverInStock());
        String listingId = book.getISBN() + listingIdAppender;
        expectedMap.put(listingId, listing);
        actualMap = fileListingDAO.getListingInfo();
        assertEquals(actualMap.keySet(), expectedMap.keySet());
        assertEquals(listing.getSeller(), actualMap.get(listingId).getSeller());
        assertEquals(listing.getBookPhoto(), actualMap.get(listingId).getBookPhoto());
        assertEquals(listing.getCreationTime(), actualMap.get(listingId).getCreationTime());
        assertEquals(listing.getBook(), actualMap.get(listingId).getBook());
        assertEquals(listing.getCondition(), actualMap.get(listingId).getCondition());
        assertEquals(listing.getPathId(), actualMap.get(listingId).getPathId());

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