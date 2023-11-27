package entity;

import java.time.LocalDateTime;

/*A seller's listing of a book. Books must be available in the OpenLibrary Database, be
* attached to a CommonUser, and describe the book's condition (e.g. Excellent, Good, or Poor).
* Users will be able to upload one photo per listing.*/

public class Listing {
    private final Book book;
    private final LocalDateTime creationTime;
    private Photo bookPhoto;
    private double listingPrice;
    private String condition;
    private final CommonUser seller;

    Listing(Book book, CommonUser seller, double listingPrice, String condition, Photo bookPhoto, LocalDateTime creationTime) {
        this.book = book;
        this.seller = seller;
        this.creationTime = creationTime;
        this.listingPrice = listingPrice;
        this.condition = condition;
        this.bookPhoto = bookPhoto;
    }

    public double getPrice(){
        return listingPrice;
    }

    public void setPrice(double newPrice){
        this.listingPrice = newPrice;
    }
    public String getCondition(){
        return this.condition;
    }
    public void setCondition(String newCondition){
        this.condition = newCondition;
    }

}
