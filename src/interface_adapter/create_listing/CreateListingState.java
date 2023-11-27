package interface_adapter.create_listing;

import entity.Book;
import entity.CommonUser;
import entity.Photo;
import interface_adapter.signup.SignupState;

public class CreateListingState {
    private Book book;
    private CommonUser seller;
    private double listingPrice;
    private String condition;
    private Photo bookPhoto;
    public CreateListingState(CreateListingState copy) {
        book = copy.book;
        seller = copy.seller;
        listingPrice = copy.listingPrice;
        condition = copy.condition;
        bookPhoto = copy.bookPhoto;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
        email = copy.email;
        phoneNumber = copy.phoneNumber;
        city = copy.city;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SignupState() {
    }
}
