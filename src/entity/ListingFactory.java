package entity;

import java.io.File;
import java.time.LocalDateTime;
public class ListingFactory {

    /**
     * Returns a new Listing with the given parameter
     * @param title title of the Book the Listing is for
     * @param ISBN ISBN of the Book the Listing is for
     * @param seller seller of the Listing
     * @param listing_price price of the Book the Listing is for
     * @param condition condition of the Book the Listing is for
     * @param bookPhoto photo showing the Book in the Listing
     * @param creationTime time the Listing was created
     * @return A new Listing
     */
    public Listing create(String title, String ISBN, String seller, double listing_price, String condition, File bookPhoto, LocalDateTime creationTime){
        return new Listing(ISBN, title, seller, listing_price, condition, bookPhoto, creationTime);
    }
}
