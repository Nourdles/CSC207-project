package entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
public class Listing {
    private final String ISBN;
    private final String title;
    private final LocalDateTime creationTime;
    private final String listingId;
    private File bookPhoto;
    private double listingPrice;
    private String condition;
    private final String seller;
    private String pathId;

    /** A seller's listing of a book.
     * Requires: book must be in the OpenLibraryDatabase
     * Users may upload up to one photo per listing.
     */
    Listing(String ISBN, String title, String seller, double listingPrice, String condition, File bookPhoto, LocalDateTime creationTime) {
        this.ISBN = ISBN;
        this.title = title;
        this.seller = seller;
        this.creationTime = creationTime;
        this.listingPrice = listingPrice;
        this.condition = condition;
        this.bookPhoto = bookPhoto;
        this.listingId = String.valueOf(this.ISBN); // + String.valueOf(book.getEverInStock());
        this.pathId = seller + "/" + this.ISBN + ".png";
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

    public String getISBN() {return ISBN;}

    public String getTitle() {return title;}

    public File getBookPhoto() {return bookPhoto;}
    public void setBookPhoto(File bookPhoto) {this.bookPhoto = bookPhoto;}
    public String getSeller() {return seller;}
    public LocalDateTime getCreationTime() {return creationTime; }
}

