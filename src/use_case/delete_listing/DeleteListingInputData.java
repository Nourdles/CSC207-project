package use_case.delete_listing;

public class DeleteListingInputData {
    final private String listingId;

    /**
     * Create a new Delete Listing Input Data with the given parameter
     * @param listingId String that represents the ID of the Listing we want to delete
     */
    public DeleteListingInputData(String listingId){
        this.listingId = listingId;
    }

    /**
     * Returns the ID of the Listing slatted for deletion, stored in Input Data
     * @return String that represents the Listing ID stored in Input Data
     */
    String getListingId(){
        return listingId;
    }
}
