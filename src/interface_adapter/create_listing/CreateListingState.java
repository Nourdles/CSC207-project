package interface_adapter.create_listing;

import java.io.File;

public class CreateListingState {
    private String title;
    private String bookISBN;
    private double listingPrice;
    private String condition;
    private File bookPhoto;
    private String seller;

    /**
     * Create a new Create Listing State
     */
    public CreateListingState() {}

    /**
     * Set the price of the Listing in the current State
     * @param listingPrice double representing the new State Listing price
     */
    public void setListingPrice(double listingPrice) { this.listingPrice = listingPrice;}

    /**
     * Returns the price of the Listing representing by the current State
     * @return double representing the price of the Listing representing by the current State
     */
    public double getListingPrice(){ return listingPrice;}

    /**
     * Sets the condition of the Listing in the current State to the given String
     * @param condition String representing the condition of the Listing represented by the current State
     */
    public void setCondition(String condition) { this.condition = condition;}

    /**
     * Returns the condition of the Listing in the current State
     * @return String representing the condition of the Listing in the current State
     */
    public String getCondition(){ return condition;}

    /**
     * Set the photo of the Listing in the current State to the given File
     * @param bookPhoto File representing the photo of the Listing in the current State
     */
    public void setBookPhoto(File bookPhoto) {this.bookPhoto = bookPhoto;}

    /**
     * Returns the photo of the Listing in the current State
     * @return A File representing the photo of the Listing in the current State
     */
    public File getBookPhoto(){return bookPhoto;}

    /**
     * Returns the ISBN of the Listing in the current State
     * @return A String representing the ISBN of the Listing in the current State
     */
    public String getBookISBN(){return bookISBN;}

    /**
     * Sets the ISBN of the Listing in the current State to the given String
     * @param ISBN String we want to set the ISBN of the Listing in the current State to
     */
    public void setBookISBN(String ISBN){this.bookISBN = ISBN;}

    /**
     * Returns the seller of the Listing in the current State
     * @return A String representing the seller of the Listing in the current State
     */
    public String getSeller(){return seller;}

    /**
     * Set the title of the Listing in the current State to the given String
     * @param title String we want to set the title of the Listing in the current State to
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * Returns the title of the Listing in the current State
     * @return A String representing the title of the Listing in the current State
     */
    public String getTitle(){return this.title;}

    /**
     * Set the seller of the Listing in the current State to the given String
     * @param seller String we want to set the seller of the Listing in the current State to
     */
    public void setSeller(String seller) {this.seller = seller;}
}
