package use_case.delete_listing;

public class DeleteListingOutputData {
    private final String deleted_listing;
    public DeleteListingOutputData(String deleted_listing){
        this.deleted_listing = deleted_listing;
    }
    public String getDeleted_listing(){return deleted_listing;}
}
