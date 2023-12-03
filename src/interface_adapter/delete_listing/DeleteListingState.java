package interface_adapter.delete_listing;

public class DeleteListingState {
    private String listing = "";
    public DeleteListingState(DeleteListingState copy){
        listing = copy.listing;
    }
    public DeleteListingState(){

    }
    public String getListing(){return listing;}
    public void setListing(String listing){
        this.listing = listing;
    }
    public String toString(){
        return "DeleteListingState{"+
                "listingId='" + listing + '\''+
                '}';
    }
}
