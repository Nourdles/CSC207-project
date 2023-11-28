package entity;
import entity.User;

import java.time.LocalDateTime;

public class CommonUser implements User {
    private final String username;
    private final String password;
    private final String email;
    private final String phoneNumber;
    private final double performanceRating;
    private final int numRatings;
    private final String city;
    private final LocalDateTime creationTime;

    /*Common Users need to provide a phoneNumber, an email, or both. */

    /*Common User provides both.*/
    CommonUser(String username, String password, LocalDateTime creationTime, String email, String phoneNumber, String city){
        this.username = username;
        this.password = password;
        this.email = email;
        this.performanceRating = 0.0;
        this.numRatings = 0;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.creationTime = creationTime;
    }


    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getEmail(){
        return email;
    }

    public void setPhoneNumber(int newPhoneNumber){
        this.phoneNumber= newPhoneNumber;
    }

    public int getPhoneNumber(){
        return phoneNumber;
    }

    public int getNumRatings() {
        return numRatings;
    }
    public double getPerformanceRating() {
        return performanceRating;
    }
    public void setCity(String newCity){
        this.city = newCity;
    }
    public String getCity(){
        return city;
    }
    @Override
    public LocalDateTime getCreationTime(){
        return creationTime;
    }
}
