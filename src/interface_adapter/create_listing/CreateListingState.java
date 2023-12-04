package interface_adapter.create_listing;

import entity.Book;
import entity.CommonUser;

import java.io.File;

public class CreateListingState {
    private Book book;
    private CommonUser seller;
    private double listingPrice;
    private String condition;
    private File bookPhoto;
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
    public double getListingPrice(){ return listingPrice;}
    public void setCondition(String condition) { this.condition = condition;}
    public String getCondition(){ return condition;}
    public void setBookPhoto(File bookPhoto) {this.bookPhoto = bookPhoto;}
    public File getBookPhoto(){return bookPhoto;}
    public Book getBook(){return book;}
    public CommonUser getSeller(){return seller;}
}
