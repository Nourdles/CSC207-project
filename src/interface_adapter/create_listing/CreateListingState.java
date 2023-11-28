package interface_adapter.create_listing;

import entity.Book;
import entity.CommonUser;
import entity.Photo;

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
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateListingState() {
    }
    public void setListingPrice(double listingPrice) { this.listingPrice = listingPrice;}
    public double getListingPrice(){ return this.listingPrice;}
    public void setCondition(String condition) { this.condition = condition;}
    public String getCondition(){ return this.condition;}
    public void setBookPhoto(Photo bookPhoto) {this.bookPhoto = bookPhoto;}
    public Photo getBookPhoto(){return this.bookPhoto;}
}
