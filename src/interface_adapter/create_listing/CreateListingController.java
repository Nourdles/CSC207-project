package interface_adapter.create_listing;

import entity.Book;
import entity.CommonUser;
import entity.Photo;
import use_case.create_listing.CreateListingInputBoundary;
import use_case.create_listing.CreateListingInputData;
import use_case.create_listing.CreateListingInteractor;

import java.time.LocalDateTime;

public class CreateListingController {

    final CreateListingInputBoundary createListingInteractor;
    public CreateListingController(CreateListingInputBoundary createListingInteractor) {
        this.createListingInteractor = createListingInteractor;
    }
    public void execute(Book book, CommonUser seller, double listingPrice, String condition, Photo bookPhoto, LocalDateTime ltd) {
        CreateListingInputData createListingInputData = new CreateListingInputData(
                book, seller, listingPrice, condition, bookPhoto, ltd);
        CreateListingInteractor.execute(createListingInputData);
    }
}
