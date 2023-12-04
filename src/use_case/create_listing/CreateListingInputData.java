package use_case.create_listing;

import entity.Book;
import entity.CommonUser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;

public class  CreateListingInputData {

    final private CommonUser seller;
    final private Book book;
    private String condition;
    private double listingPrice;
    private File bookPhoto;
    private String imgPath;
    public CreateListingInputData(Book book, CommonUser seller, double listingPrice, String condition, File bookPhoto,
                                  LocalDateTime ltd) {
        this.book = book;
        this.seller = seller;
        this.listingPrice = listingPrice;
        this.condition = condition;
        this.bookPhoto = bookPhoto;
    }

    /*Methods to retrieve listing data: cannot edit, edit listing is another use case. */
    Book getBook(){
        return book;
    }
    CommonUser getSeller(){
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
