package interface_adapter.delete_listing;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.delete_listing.DeleteListingInputBoundary;
import use_case.delete_listing.DeleteListingInputData;

import static org.junit.jupiter.api.Assertions.*;

class DeleteListingControllerTest {
    private DeleteListingInputBoundary inputBoundaryMock;
    private DeleteListingController controller;

    @Test
    void execute() {
        inputBoundaryMock = Mockito.mock(DeleteListingInputBoundary.class);
        controller = new DeleteListingController(inputBoundaryMock);
        controller.execute("listingID");
        Mockito.verify(inputBoundaryMock).execute(Mockito.any(DeleteListingInputData.class));
    }
}