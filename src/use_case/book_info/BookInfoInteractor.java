package use_case.book_info;


import entity.Listing;
import entity.CommonUser;

import java.util.ArrayList;
import java.util.List;

public class BookInfoInteractor implements BookInfoInputBoundary{
    final BookInfoOutputBoundary bookInfoOutputPresenter;
    final BookInfoDataAccessInterface bookInfoDataAccessInterface;

    public BookInfoInteractor(BookInfoOutputBoundary bookInfoOuputPresenter, BookInfoDataAccessInterface bookInfoDataAccessInterface) {
        this.bookInfoOutputPresenter = bookInfoOuputPresenter;
        this.bookInfoDataAccessInterface = bookInfoDataAccessInterface;
    }


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
                listingDetails.add(bookInfoDataAccessInterface.findUserByUsername(listing.getSeller()).getCity());
                listingDetails.add(bookInfoDataAccessInterface.findUserByUsername(listing.getSeller()).getEmail());
                listingDetails.add(bookInfoDataAccessInterface.findUserByUsername(listing.getSeller()).getPhoneNumber());
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
