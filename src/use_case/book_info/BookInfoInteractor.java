package use_case.book_info;

import data_access.FileListingDataAccessObject;
import entity.Listing;

import java.util.ArrayList;
import java.util.List;

public class BookInfoInteractor implements BookInfoInputBoundary{
    final BookInfoOutputBoundary bookInfoOutputPresenter;
    final BookInfoDataAccessInterface bookInfoDataAccessInterface;

    /**
     * Create a new Book Info Interactor with the given parameters
     * @param bookInfoOutputPresenter Book Info Output Boundary
     * @param bookInfoDataAccessInterface Book Info Data Access Interface
     */
    public BookInfoInteractor(BookInfoOutputBoundary bookInfoOutputPresenter, BookInfoDataAccessInterface bookInfoDataAccessInterface) {
        this.bookInfoOutputPresenter = bookInfoOutputPresenter;
        this.bookInfoDataAccessInterface = bookInfoDataAccessInterface;
    }

    /**
     * If there are no exceptions, calls the getBookListings method of the Data Access Interface, then creates a List of
     * Lists with the information of every Listing of that Book, creates a new Output Data with it, then calls the
     * displayBookInfo method of the Presenter. If there is an exception, creates a new Output Data for an error state
     * and calls displayBookInfo in the Presenter with that error data.
     * @param inputData Book Info Input Data
     */
    @Override
    public void showBookDetails(BookInfoInputData inputData) {
        try {
            List<Listing> listings = bookInfoDataAccessInterface.getBookListings(inputData.getISBN());
            List<List<String>> listingsInfo = new ArrayList<>();

            for (Listing listing : listings) {
                List<String> listingDetails = new ArrayList<>();
                listingDetails.add(listing.getSeller());
                listingDetails.add(String.valueOf(listing.getPrice()));
                listingDetails.add(listing.getCondition());
                listingDetails.add(bookInfoDataAccessInterface.findCity(listing.getSeller()));
                listingDetails.add(bookInfoDataAccessInterface.findEmail(listing.getSeller()));
                listingDetails.add(bookInfoDataAccessInterface.findPhoneNumber(listing.getSeller()));
                listingsInfo.add(listingDetails);
            }

            BookInfoOutputData outputData = new BookInfoOutputData(
                    inputData.getTitle(),
                    inputData.getYear(),
                    inputData.getAuthor(),
                    inputData.getISBN(),
                    inputData.getCoverURL(),
                    inputData.getLanguage(),
                    inputData.getSubjects(),
                    listingsInfo,
                    null
            );
            bookInfoOutputPresenter.displayBookInfo(outputData);

        } catch (Exception e) {
            BookInfoOutputData errorOutputData = new BookInfoOutputData(
                    null, 0, null, null, null, null, null, null, e.getMessage()
            );
            bookInfoOutputPresenter.displayBookInfo(errorOutputData);

        }

    }
}
