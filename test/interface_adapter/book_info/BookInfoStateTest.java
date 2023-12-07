package interface_adapter.book_info;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookInfoStateTest {
    private BookInfoState state = new BookInfoState();

    @Test
    void setErrorMessage() {
        state.setErrorMessage("error");
        assertEquals("error", state.getErrorMessage());
    }

    @Test
    void getErrorMessage() {
        assertEquals("Could not retrieve book information", state.getErrorMessage());
    }

    @Test
    void setYear() {
        state.setYear(2000);
        assertEquals(2000, state.getYear());
    }

    @Test
    void setTitle() {
        state.setTitle("Matilda");
        assertEquals("Matilda", state.getTitle());
    }

    @Test
    void setAuthor() {
        state.setAuthor("Bram Stoker");
        assertEquals("Bram Stoker", state.getAuthor());
    }

    @Test
    void setISBN() {
        state.setISBN("123");
        assertEquals("123", state.getISBN());
    }

    @Test
    void setCoverURL() {
        state.setCoverURL("url");
        assertEquals("url", state.getCoverURL());
    }

    @Test
    void setLanguage() {
        state.setLanguage("en");
        assertEquals("en", state.getLanguage());
    }

    @Test
    void setSubjects() {
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("horror");
        state.setSubjects(subjects);
        assertEquals(subjects, state.getSubjects());
    }

    @Test
    void getTitle() {
        assertNull(state.getTitle());
    }

    @Test
    void getAuthor() {
        assertNull(state.getAuthor());
    }

    @Test
    void getYear() {
        state.setYear(2000);
        assertEquals(2000, state.getYear());
    }

    @Test
    void getISBN() {
        assertNull(state.getISBN());
    }

    @Test
    void getLanguage() {
        assertNull(state.getLanguage());
    }

    @Test
    void getCoverURL() {
        assertNull(state.getCoverURL());
    }

    @Test
    void getSubjects() {
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("horror");
        state.setSubjects(subjects);
        assertEquals(subjects, state.getSubjects());
    }

    @Test
    void setListingsInfo() {
        List<List<String>> listings = new ArrayList<>();
        List<String> listing = new ArrayList<>();
        listing.add("Unu");
        listing.add("23.0");
        listing.add("Good");
        listing.add(null);
        listing.add(null);
        listing.add(null);
        listings.add(listing);
        state.setListingsInfo(listings);
        assertEquals(listings, state.getListingsInfo());
    }

    @Test
    void getListingsInfo() {
        List<List<String>> listings = new ArrayList<>();
        List<String> listing = new ArrayList<>();
        listing.add("Unu");
        listing.add("23.0");
        listing.add("Good");
        listing.add(null);
        listing.add(null);
        listing.add(null);
        listings.add(listing);
        state.setListingsInfo(listings);
        assertEquals(listings, state.getListingsInfo());
    }
}