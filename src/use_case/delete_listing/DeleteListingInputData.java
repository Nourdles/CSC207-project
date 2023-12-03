package use_case.delete_listing;

public class DeleteListingInputData {
    final private String listingId;

    public DeleteListingInputData(String listingId){
        this.listingId = listingId;
    }
    String getListingId(){
        return listingId;
    }
}
