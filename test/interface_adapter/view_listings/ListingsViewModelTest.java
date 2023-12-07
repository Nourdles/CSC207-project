package interface_adapter.view_listings;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListingsViewModelTest {
    private ListingsViewModel model = new ListingsViewModel();

    @Test
    void setState() {
        ListingsState state = new ListingsState();
        model.setState(state);
        assertEquals(state, model.getState());
    }

    @Test
    void getState() {
        ListingsState state = new ListingsState();
        model.setState(state);
        assertEquals(state, model.getState());
    }

    @Test
    void getListings() {
        assertNull(model.getListings());
    }

    @Test
    void setListings() {
        model.setListings("");
        assertEquals("", model.getListings());
    }
}