package use_case.create_listing;

import java.io.File;
import java.time.LocalDateTime;

public class  CreateListingInputData {

    final private String seller;
    final private String title;
    final private String ISBN;
    private final String condition;
    private final double listingPrice;
    private final File bookPhoto;

    /**
     * Creates a new Create Listing Input Data
     * @param title String that represents the title of the Book the Listing is being created for
     * @param ISBN String that represents the ISBN of the Book the Listing is being created for
     * @param seller String that represents the username of the currently logged-in User
     * @param listingPrice double that represents the price of the Book the Listing is being created for
     * @param condition String that represents the condition of the Book the Listing is being created for
     * @param bookPhoto File that represents a photo of the Book the Listing is being created for
     */
    public CreateListingInputData(String title, String ISBN, String seller, double listingPrice, String condition, File bookPhoto) {
        this.ISBN = ISBN;
        this.seller = seller;
        this.listingPrice = listingPrice;
        this.condition = condition;
        this.bookPhoto = bookPhoto;
        this.title = title;
    }

    /**
     * Returns the title of the Book the Listing is being created for, which is stored in Input Data
     * @return String that represents the title of the Book of the listing in Input Data
     */
    public String getTitle(){return this.title;}

    /**
     * Returns the ISBN of the Book the Listing is being created for, which is stored in Input Data
     * @return String that represents the ISBN of the Book of the listing in Input Data
     */
    public String getBookISBN(){return this.ISBN;}

    /**
     * Returns the username of the currently logged-in User, stored in Input Data
     * @return String that represents the username of the logged-in User in Input Data
     */
    public String getSeller(){
        return seller;
    }

    /**
     * Returns the price of the Book the Listing is being created for, which is stored in Input Data
     * @return double that represents the price of the Book of the listing in Input Data
     */
    public double getListingPrice(){
        return listingPrice;
    }

    /**
     * Returns the condition of the Book the Listing is being created for, which is stored in Input Data
     * @return String that represents the condition of the Book of the listing in Input Data
     */
    public String getCondition(){
        return condition;
    }

    /**
     * Returns a photo of the Book the Listing is being created for, which is stored in Input Data
     * @return File that represents a photo of the Book of the listing in Input Data
     */
    public File getBookPhoto(){
        return bookPhoto;
    }

    /**
     * Returns the time when the Listing is created
     * @return LocalDateTime that represents when the Listing is created
     */
    public LocalDateTime getCreationTime(){return LocalDateTime.now();}
}
