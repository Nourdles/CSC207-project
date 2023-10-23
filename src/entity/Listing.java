package src.entity;

import entity.CommonUser;

import java.time.LocalDateTime;

public class Listing {
    private final Book book;
    private final LocalDateTime creationTime;

    private double listingPrice;

    private String condition;

    private final CommonUser seller;

    Listing(Book book, CommonUser seller, double listingPrice, String condition, LocalDateTime creationTime) {
        this.book = book;
        this.seller = seller;
        this.creationTime = creationTime;
        this.listingPrice = listingPrice;
        this.condition = condition;

        /* Add attribute for book photos*/

    }

    public double getPrice(){
        return listingPrice;
    }

    public void setPrice(double newPrice){
        this.listingPrice = newPrice;
    }


    public String getCondition(){
        return this.condition;
    }

    public void setCondition(String newCondition){
        this.condition = newCondition;
    }

}
