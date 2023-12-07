package interface_adapter.view_listings;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListingsStateTest {
    private ListingsState state = new ListingsState();

    @Test
    void getListingsString() {
        assertEquals(new ArrayList<>(), state.getListingsString());
    }

    @Test
    void getListings() {
        assertEquals(new ArrayList<>(), state.getListings());
    }

    @Test
    void setListings() {
        state.setListings(new ArrayList<>());
        assertEquals(new ArrayList<>(), state.getListings());
    }

    @Test
    void setUsername() {
        state.setUsername("user");
        assertEquals("user", state.getUsername());
    }

    @Test
    void getUsername() {
        assertEquals("", state.getUsername());
    }
}