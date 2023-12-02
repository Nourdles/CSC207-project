package interface_adapter.create_listing;

import entity.Book;
import entity.CommonUser;
import use_case.create_listing.CreateListingInputBoundary;
import use_case.create_listing.CreateListingInputData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateListingController {

    final CreateListingInputBoundary createListingInteractor;
    public CreateListingController(CreateListingInputBoundary createListingInteractor) {
        this.createListingInteractor = createListingInteractor;
    }
    public void execute(Book book, CommonUser seller, double listingPrice, String condition, File bookPhoto, LocalDateTime ltd) throws IOException {
        CreateListingInputData createListingInputData = new CreateListingInputData(
                book, seller, listingPrice, condition, bookPhoto, ltd);
        createListingInteractor.execute(createListingInputData);
    }
}
