package use_case.book_info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookInfoOutputDataTest {
    private BookInfoOutputData outputData;

    @BeforeEach
    void init() {
        List<List<String>> listings = new ArrayList<>();
        List<String> listing = new ArrayList<>();
        listing.add("Unu");
        listing.add("23.0");
        listing.add("Good");
        listing.add(null);
        listing.add(null);
        listing.add(null);
        listings.add(listing);
        outputData = new BookInfoOutputData("Dracula", 1989, "Bram Stoker", "123", "url",
                "english", new ArrayList<>(), listings, null);
    }

    @Test
    void isSuccess() {
        assertTrue(outputData.isSuccess());
    }

    @Test
    void getSubjects() {
        assertEquals(new ArrayList<>(), outputData.getSubjects());
    }

    @Test
    void getTitle() {
        assertEquals("Dracula", outputData.getTitle());
    }

    @Test
    void getListingsDetails() {
        List<List<String>> listings = new ArrayList<>();
        List<String> listing = new ArrayList<>();
        listing.add("Unu");
        listing.add("23.0");
        listing.add("Good");
        listing.add(null);
        listing.add(null);
        listing.add(null);
        listings.add(listing);
        assertEquals(listings, outputData.getListingsDetails());
    }

    @Test
    void getAuthor() {
        assertEquals("Bram Stoker", outputData.getAuthor());
    }

    @Test
    void getCoverURL() {
        assertEquals("url", outputData.getCoverURL());
    }

    @Test
    void getISBN() {
        assertEquals("123", outputData.getISBN());
    }

    @Test
    void getLanguage() {
        assertEquals("english", outputData.getLanguage());
    }

    @Test
    void getYear() {
        assertEquals(1989, outputData.getYear());
    }

    @Test
    void getErrorMessage() {
        assertThrows(IllegalStateException.class, () -> outputData.getErrorMessage());
    }
}