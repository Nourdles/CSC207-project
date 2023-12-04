package use_case.create_listing;

import entity.CommonUser;

import java.io.File;
import java.time.LocalDateTime;

public class  CreateListingInputData {

    final private String seller;
    final private String title;
    final private String ISBN;
    private String condition;
    private double listingPrice;
    private File bookPhoto;
    private String imgPath;
    public CreateListingInputData(String title, String ISBN, String seller, double listingPrice, String condition, File bookPhoto,
                                  LocalDateTime ltd) {
        this.ISBN = ISBN;
        this.seller = seller;
        this.listingPrice = listingPrice;
        this.condition = condition;
        this.bookPhoto = bookPhoto;
        this.title = title;
    }

    /*Methods to retrieve listing data: cannot edit, edit listing is another use case. */
    String getTitle(){return this.title;}
    String getBookISBN(){return this.ISBN;}
    String getSeller(){
        return seller;
    }
    double getListingPrice(){
        return listingPrice;
    }
    String getCondition(){
        return condition;
    }
    File getBookPhoto(){
        return bookPhoto;
    }
    LocalDateTime getCreationTime(){return LocalDateTime.now();}
}
