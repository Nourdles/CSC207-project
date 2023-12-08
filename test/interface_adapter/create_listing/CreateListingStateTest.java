package interface_adapter.create_listing;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CreateListingStateTest {
    private CreateListingState state = new CreateListingState();

    @Test
    void setListingPrice() {
        state.setListingPrice(30.0);
        assertEquals(30.0, state.getListingPrice());
    }

    @Test
    void getListingPrice() {
        assertEquals(0.0, state.getListingPrice());
    }

    @Test
    void setCondition() {
        state.setCondition("Poor");
        assertEquals("Poor", state.getCondition());
    }

    @Test
    void getCondition() {
        assertNull(state.getCondition());
    }

    @Test
    void setBookPhoto() {
        File file = new File("demo.txt");
        state.setBookPhoto(file);
        assertEquals(file, state.getBookPhoto());
    }

    @Test
    void getBookPhoto() {
        assertNull(state.getBookPhoto());
    }

    @Test
    void getBookISBN() {
        assertNull(state.getBookISBN());
    }

    @Test
    void setBookISBN() {
        state.setBookISBN("123");
        assertEquals("123", state.getBookISBN());
    }

    @Test
    void getSeller() {
        assertNull(state.getSeller());
    }

    @Test
    void setTitle() {
        state.setTitle("Dracula");
        assertEquals("Dracula", state.getTitle());
    }

    @Test
    void getTitle() {
        assertNull(state.getTitle());
    }

    @Test
    void setSeller() {
        state.setSeller("user");
        assertEquals("user", state.getSeller());
    }
}