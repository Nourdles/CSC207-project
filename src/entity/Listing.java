package entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
public class Listing {
    private final Book book;
    private final LocalDateTime creationTime;
    private final String listingId;
    private File bookPhoto;
    private double listingPrice;
    private String condition;
    private final CommonUser seller;
    private String pathId;

    /** A seller's listing of a book.
     * Requires: book must be in the OpenLibraryDatabase
     * Users may upload up to one photo per listing.
     * @param book the book associated with the listing.
     * @param seller the User offering a book for sale.
     * @param listingPrice the (possibly initial) price the seller is demanding in exchange for the book.
     * @param bookPhoto either a default image or an uploaded photo of the book shot by the seller.
     * @param creationTime the time the listing is created.
     */
    Listing(Book book, CommonUser seller, double listingPrice, String condition, File bookPhoto, LocalDateTime creationTime) {
        this.book = book;
        this.seller = seller;
        this.creationTime = creationTime;
        this.listingPrice = listingPrice;
        this.condition = condition;
        this.bookPhoto = bookPhoto;
        this.listingId = book.getISBN() + String.valueOf(book.getEverInStock());
        this.pathId = seller.getUsername() + "/" + book.getISBN() + ".png";
    }
    public double getPrice(){
        return listingPrice;
    }
    public void setPrice(double newPrice){
        this.listingPrice = newPrice;
    }
    public String getPathId() {return pathId;}
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
    public File getBookPhoto() {return bookPhoto;}
    public void setBookPhoto(File bookPhoto) {this.bookPhoto = bookPhoto;}
    public CommonUser getSeller() {return seller; }
    public LocalDateTime getCreationTime() {return creationTime; }
}

