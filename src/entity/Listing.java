package entity;

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

    /**
     * Returns the price of the Listing
     * @return the price of the Listing
     */
    public double getPrice(){
        return listingPrice;
    }

    /**
     * Returns the path ID for the picture of the Listing
     * @return the path ID for the picture of the Listing
     */
    public String getPathId() {return pathId;}

    /**
     * Returns the Book condition of the Listing
     * @return the Book condition of the Listing
     */
    public String getCondition(){
        return this.condition;
    }

    /**
     * Set the condition of the Book in the Listing
     * @param newCondition String we want to set the Book condition to
     */
    public void setCondition(String newCondition){
        this.condition = newCondition;
    }

    /**
     * Returns the ID of the Listing
     * @return the ID of the Listing
     */
    public String getListingId(){
        return listingId;
    }

    /**
     * Returns the ISBN of the Book in the Listing
     * @return the ISBN of the Book in the Listing
     */
    public String getISBN() {return ISBN;}

    /**
     * Returns the Book title of the Listing
     * @return the Book title of the Listing
     */
    public String getTitle() {return title;}

    /**
     * Returns the Book photo of the Listing
     * @return the Book photo of the Listing
     */
    public File getBookPhoto() {return bookPhoto;}

    /**
     * Returns the seller of the Listing
     * @return the seller of the Listing
     */
    public String getSeller() {return seller;}

    /**
     * Returns the creation time of the Listing
     * @return the creation time of the Listing
     */
    public LocalDateTime getCreationTime() {return creationTime; }
}

