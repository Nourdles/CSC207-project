package entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
public class ListingFactory {
    /**
     * A Factory for creating Listings
     * @param title: Title of a book object.
     * @param ISBN: ISBN of a book object.
     * @param seller: a CommonUser listing the Book for sale.
     * @param listing_price: the price the book is listed for sale at.
     * @param condition: a String descriptor of the Book's condition.
     * @param bookPhoto: a Photo representing an image of a Book.
     * @param creationTime: the time the Listing was created.
     */
    public Listing create(String title, String ISBN, String seller, double listing_price, String condition, File bookPhoto, LocalDateTime creationTime){
        return new Listing(ISBN, title, seller, listing_price, condition, bookPhoto, creationTime);
    }
}
