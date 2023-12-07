package use_case.delete_listing;

public class DeleteListingOutputData {
    private final String deleted_listing;

    /**
     * Create a new Delete Listing Output Data with the given parameter
     * @param deleted_listing String that represents the ID of the deleted Listing
     */
    public DeleteListingOutputData(String deleted_listing){
        this.deleted_listing = deleted_listing;
    }

    /**
     * Returns the ID of the deleted Listing, stored in Output Data
     * @return String that represents the ID of the deleted Listing stored in Output Data
     */
    public String getDeleted_listing(){return deleted_listing;}
}
