package src.entity;
import entity.User;

public class CommonUser implements User {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private double performanceRating;
    private int numRatings;
    private String city;
    CommonUser(String username, String password, String email, String phoneNumber, String city){
        this.username = username;
        this.password = password;
        this.email = email;
        this.performanceRating = 0.0;
        this.numRatings = 0;
        this.city = city;
    }


    CommonUser(String username, String password, String phoneNumber, String city){
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.performanceRating = 0.0;
        this.numRatings = 0;
        this.city = city;
    }

    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    public String getUsername(){
        return username;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public String getEmail(){
        return email;
    }

    public void setPhoneNumber(String newPhoneNumber){
        this.phoneNumber= newPhoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public void setPerformanceRating(double performanceRating) {
        this.performanceRating = performanceRating;
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

}
