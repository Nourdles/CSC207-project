package interface_adapter.delete_listing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteListingStateTest {
    private DeleteListingState state = new DeleteListingState();

    @Test
    void getListing() {
        assertEquals("", state.getListing());
    }

    @Test
    void setListing() {
        state.setListing("listingID");
        assertEquals("listingID", state.getListing());
    }

    @Test
    void testToString() {
        assertEquals("DeleteListingState{"+
                "listingId='" + "" + '\''+
                '}', "DeleteListingState{"+
                "listingId='" + state.getListing() + '\''+
                '}'
        );
    }
}