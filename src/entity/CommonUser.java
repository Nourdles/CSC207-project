package entity;
import entity.User;

import java.time.LocalDateTime;

public class CommonUser implements User {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private double performanceRating;
    private int numRatings;
    private String city;
    private LocalDateTime creationTime;

    /*Common Users need to provide a phoneNumber, an email, or both. */

    /*Common User provides both.*/

    /**
     * A CommonUser of the system: They can browse others' listings or create/delete their own.
     * @param username
     * @param password
     * @param creationTime
     * @param email
     * @param phoneNumber
     * @param city
     */
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

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int getNumRatings() {
        return numRatings;
    }
    public double getPerformanceRating() {
        return performanceRating;
    }
    public String getCity(){
        return city;
    }
    @Override
    public LocalDateTime getCreationTime(){
        return creationTime;
    }
    @Override
    public void setUsername(String username) {this.username = username;}
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public void setCity(String city) {
        this.city = city;
    }
}
