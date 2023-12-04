package interface_adapter.create_listing;

import entity.Book;
import entity.CommonUser;

import java.io.File;

public class CreateListingState {
    private String title;
    private String bookISBN;
    private double listingPrice;
    private String condition;
    private File bookPhoto;
    private String seller;
    public CreateListingState(CreateListingState copy) {
        bookISBN = copy.bookISBN;
        seller = copy.seller;
        listingPrice = copy.listingPrice;
        condition = copy.condition;
        bookPhoto = copy.bookPhoto;
        title = copy.title;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateListingState() {}
    public void setListingPrice(double listingPrice) { this.listingPrice = listingPrice;}
    public double getListingPrice(){ return listingPrice;}
    public void setCondition(String condition) { this.condition = condition;}
    public String getCondition(){ return condition;}
    public void setBookPhoto(File bookPhoto) {this.bookPhoto = bookPhoto;}
    public File getBookPhoto(){return bookPhoto;}
    public String getBookISBN(){return bookISBN;}

    public void setBookISBN(String ISBN){this.bookISBN = ISBN;}
    public String getSeller(){return seller;}
    public void setTitle(String title) {this.title = title;}
    public String getTitle(){return this.title;}

    public void setSeller(String seller) {this.seller = seller;}
}
