package entity;

import entity.CommonUser;
import entity.Book;
import entity.Photo;

import java.time.LocalDateTime;
public class Listing {
    private final Book book;
    private final LocalDateTime creationTime;
    private final String listingId;
    private Photo bookPhoto;
    private double listingPrice;
    private String condition;
    private final CommonUser seller;

    /** A seller's listing of a book.
     * Requires: book must be in the OpenLibraryDatabase
     * Users may upload up to one photo per listing.
     */
    Listing(Book book, CommonUser seller, double listingPrice, String condition, Photo bookPhoto, LocalDateTime creationTime) {
        this.book = book;
        this.seller = seller;
        this.creationTime = creationTime;
        this.listingPrice = listingPrice;
        this.condition = condition;
        this.bookPhoto = bookPhoto;
        this.listingId = String.valueOf(book.getISBN()) + String.valueOf(book.getEverInStock());
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
    public String getListingId(){
        return listingId;
    }
    public Book getBook(){ return book; }
    public Photo getBookPhoto() {return bookPhoto;}
    public void setBookPhoto(Photo bookPhoto) {this.bookPhoto = bookPhoto;}
    public CommonUser getSeller() {return seller; }
    public LocalDateTime getCreationTime() {return creationTime; }
}

