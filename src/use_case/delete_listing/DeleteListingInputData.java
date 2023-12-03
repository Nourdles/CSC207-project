package use_case.delete_listing;

import entity.Listing;
import use_case.signup.SignupInputData;

public class DeleteListingInputData {

    private String listingId;
    public DeleteListingInputData(String listingId){
        this.listingId = listingId;
    }
    public String getListingId() {
        return this.listingId;
    }
}
