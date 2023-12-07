package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ListingTest {

    private Listing listing;

    // For testing equals() method
    private Listing listing2;

    @BeforeEach
    void init() {
        File file = new File("demo.txt");
        listing = new Listing("123", "Dracula", "user", 50.5, "Excellent", file,
                LocalDateTime.MAX);
    }

    @Test
    void getPrice() {
        assertEquals(50.5, listing.getPrice());
    }

    @Test
    void getPathId() {
        assertEquals("user/123.png", listing.getPathId());
    }

    @Test
    void getCondition() {
        assertEquals("Excellent", listing.getCondition());
    }

    @Test
    void setCondition() {
        listing.setCondition("Poor");
        assertEquals("Poor", listing.getCondition());
    }

    @Test
    void getListingId() {
        assertEquals("123", listing.getListingId());
    }

    @Test
    void getTitle() {
        assertEquals("Dracula", listing.getTitle());
    }

    @Test
    void getISBN() {
        assertEquals("123", listing.getISBN());
    }

    @Test
    void getBookPhoto() {
        File file = new File("demo.txt");
        assertEquals(file, listing.getBookPhoto());
    }

    @Test
    void getSeller() {
        assertEquals("user", listing.getSeller());
    }

    @Test
    void getCreationTime() {
        assertEquals(LocalDateTime.MAX, listing.getCreationTime());
    }
    @Test
    void equalsTrue(){
        File file = new File("demo.txt");
        listing2 = new Listing("123", "Dracula", "user", 50.5, "Excellent",
                file, LocalDateTime.MAX);
        assertTrue(listing.equals(listing2));
    };
    @Test
    void equalsFalse(){
        File file = new File("demo.txt");
        listing2 = new Listing("12345", "Dracula", "user", 50.5, "Excellent",
                file, LocalDateTime.MAX);
        assertFalse(listing.equals(listing2));
    };
    @Test
    void nonListingEqualsFalse(){
        assertNotEquals(listing, "12345");
    };
}