package interface_adapter.view_listings;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.view_listings.ListingsInputBoundary;
import use_case.view_listings.ListingsInputData;

import static org.junit.jupiter.api.Assertions.*;

class ListingsControllerTest {
    private ListingsInputBoundary inputBoundaryMock;
    private ListingsController controller;

    @Test
    void execute() {
        inputBoundaryMock = Mockito.mock(ListingsInputBoundary.class);
        controller = new ListingsController(inputBoundaryMock);
        controller.execute("username");
        Mockito.verify(inputBoundaryMock).execute(Mockito.any(ListingsInputData.class));
    }
}