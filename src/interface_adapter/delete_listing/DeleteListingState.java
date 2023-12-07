package interface_adapter.delete_listing;

public class DeleteListingState {
    private String listing = "";

    /**
     * Create a new Delete Listings State
     */
    public DeleteListingState(){

    }

    /**
     * Returns the listing in the current State
     * @return A String representing the listing in the current State
     */
    public String getListing(){return listing;}

    /**
     * Set the listing in the current State to the given String
     * @param listing String we want to set the listing in the current State to be
     */
    public void setListing(String listing){
        this.listing = listing;
    }

    /**
     * Returns a String representation of the listing ID of the listing in the State
     * @return String communicating the listing ID of the listing in the current State
     */
    public String toString(){
        return "DeleteListingState{"+
                "listingId='" + listing + '\''+
                '}';
    }
}
