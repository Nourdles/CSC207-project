package use_case.create_listing;

import java.io.IOException;

public interface CreateListingInputBoundary {
    void execute(CreateListingInputData createListingInputData) throws IOException;
}
