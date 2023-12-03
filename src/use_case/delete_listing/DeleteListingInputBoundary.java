package use_case.delete_listing;

import java.io.IOException;

public interface DeleteListingInputBoundary {
    void execute(DeleteListingInputData deleteListingInputData) throws IOException;
}
