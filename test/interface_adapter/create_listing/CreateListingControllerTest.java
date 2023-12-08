package interface_adapter.create_listing;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.create_listing.CreateListingInputBoundary;
import use_case.create_listing.CreateListingInputData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CreateListingControllerTest {
    private CreateListingInputBoundary inputBoundaryMock;
    private CreateListingController controller;

    @Test
    void execute() throws IOException {
        inputBoundaryMock = Mockito.mock(CreateListingInputBoundary.class);
        controller = new CreateListingController(inputBoundaryMock);
        File file = new File("demo.txt");
        controller.execute("title", "ISBN", "seller", 20.0, "Excellent", file,
                LocalDateTime.MAX);
        Mockito.verify(inputBoundaryMock).execute(Mockito.any(CreateListingInputData.class));
    }
}