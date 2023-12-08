package data_access;

import app.CreateListingUseCaseFactory;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.create_listing.CreateListingInteractor;
import use_case.create_listing.CreateListingOutputBoundary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the csv file that stores listing info and the directory images system for storing book photos.
 * Note: Listings' data cannot persist.
 */
class FileListingDataAccessObjectTest {
    private FileListingDataAccessObject fileListingDAO;
    private String sellerName;
    private String listingsPath;
    private ListingFactory listingFactory;
    private Listing listing;
    private String ISBN;
    private Map<String, Listing> actualMap = new HashMap<>();
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        listingFactory = new ListingFactory();
        String photoPath = "src/default.png";
        listingsPath = "listingInfo.csv";
        String bookTitle = "History";
        sellerName = "Bob Jones";
        ISBN = "1234567898765";
        File bookPhoto = new File(photoPath);
        LocalDateTime bookLDT = LocalDateTime.now();
        listing = listingFactory.create(bookTitle, ISBN, sellerName, 50, "Excellent", bookPhoto, bookLDT);
        fileListingDAO = new FileListingDataAccessObject(listingsPath, listingFactory);
    }
    @org.junit.jupiter.api.BeforeEach
    void clear() {
    }

    @org.junit.jupiter.api.Test
    void makeImagesDirectory() throws IOException {
        File file = new File("allImages");
        assertTrue(file.isDirectory());
        File[] images = file.listFiles();
        assert images != null;
        for (File image: images){
            assertTrue(image.delete());
        }
        assertTrue(file.delete());
        FileListingDataAccessObject newfileListingDAO = new FileListingDataAccessObject(listingsPath, listingFactory);
        Path dirPath = Paths.get("allImages");
        assertTrue(Files.exists(dirPath));
    }
    @org.junit.jupiter.api.Test
    void makeListingsCSV() throws IOException {
        String testListingString = "listingsTest.csv";
        Path testListingPath = Paths.get(testListingString);
        if (Files.exists(testListingPath)){
            Files.delete(testListingPath);
        }
        File file = new File(testListingString);
        assertEquals(file.length(),0);
        FileListingDataAccessObject newfileListingDAO = new FileListingDataAccessObject(testListingString, listingFactory);
        Path listingsTestPath = Paths.get("listingsTest.csv");
        assertTrue(Files.exists(listingsTestPath));
    }

    @org.junit.jupiter.api.Test
    void save() throws IOException {
        String listingId = ISBN;
        actualMap = fileListingDAO.getListingInfo();
        Map<String, Listing> expectedMap = new HashMap<>(actualMap);
        expectedMap.put(listingId, listing);
        fileListingDAO.save(listing);
        assertEquals(actualMap.size(), expectedMap.size());
        for(Listing listing: actualMap.values()){
            assertTrue(expectedMap.containsValue(listing));
        }
    }
    @org.junit.jupiter.api.Test
    void successExistsById() {
        fileListingDAO.save(listing);
        assertTrue(fileListingDAO.existsById(ISBN));
    }
    @org.junit.jupiter.api.Test
    void failExistById(){
        assertFalse(fileListingDAO.existsById("abcdefg"));
    }
    @org.junit.jupiter.api.Test
    void successDelete() throws IOException {
        actualMap = fileListingDAO.getListingInfo();
        fileListingDAO.save(listing);
        assertTrue(actualMap.containsKey(ISBN));
        assertTrue(actualMap.containsValue(listing));
        fileListingDAO.delete(ISBN);
        assertFalse(actualMap.containsKey(ISBN));
        assertFalse(actualMap.containsValue(listing));
    }

    @org.junit.jupiter.api.Test
    void getUserListings() throws IOException {
        List<Listing> expectedList = new ArrayList<>();
        fileListingDAO.save(listing);
        expectedList.add(listing);
        assertEquals(fileListingDAO.getUserListings(sellerName), expectedList);
    }
    @org.junit.jupiter.api.Test
    void getBookListings() throws IOException {
        List<Listing> expectedList = new ArrayList<>();
        expectedList.add(listing);
        fileListingDAO.save(listing);
        assertEquals(fileListingDAO.getBookListings(ISBN), expectedList);
    }

    @Test
    void findPhoneNumber() {
        assertNull(fileListingDAO.findPhoneNumber(""));
    }

    @Test
    void findEmail() {
        assertNull(fileListingDAO.findEmail(""));
    }

    @Test
    void findCity() {
        assertNull(fileListingDAO.findCity(""));
    }
}