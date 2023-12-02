package entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
public class ListingFactory {
    /**
     * A Factory for creating Listings
     * @param book: a Book object representing a book and associated data, e.g. ISBN.
     * @param seller: a CommonUser listing the Book for sale.
     * @param listing_price: the price the book is listed for sale at.
     * @param condition: a String descriptor of the Book's condition.
     * @param bookPhoto: a Photo representing an image of a Book.
     * @param creationTime: the time the Listing was created.
     */
    public Listing create(Book book, CommonUser seller, double listing_price, String condition, File bookPhoto, LocalDateTime creationTime){
        return new Listing(book, seller, listing_price, condition, bookPhoto, creationTime);
    }
}
