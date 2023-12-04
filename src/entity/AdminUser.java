package entity;
import entity.User;

import java.time.LocalDateTime;

public class AdminUser implements User {
    private String username;
    private String password;
    private String email;
    private final LocalDateTime ltd;
    private String phoneNumber;
    private String city;

    /**
     * An Admin User with administrative capabilities.
     * @param username
     * @param password
     * @param email
     * @param ltd the time the Admin's account was created.
     * @param phoneNumber
     * @param city the Admin's city of residence.
     */

    AdminUser(String username, String password, LocalDateTime ltd, String email, String phoneNumber, String city){
        this.username = username;
        this.password = password;
        this.email = email;
        this.ltd = ltd;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    /*Could add change username, password, email functionalities or use cases */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public void setEmail(String email){
        this.email = email;
    }
    @Override
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    @Override
    public void setCity(String city) {this.city = city;}
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getEmail(){
        return email;
    }
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public String getCity() {
        return city;
    }
    @Override
    public LocalDateTime getCreationTime(){
        return ltd;
    }



}
