package entity;

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
     * @param username username of the User
     * @param password password of the User
     * @param email email of the User
     * @param ltd the time the Admin's account was created.
     * @param phoneNumber phone number of the User
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

    /**
     * Set the password to be the given String
     * @param password the given String corresponding to the password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the username to be the given String
     * @param username the given String corresponding to the password
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the email to be the given String
     * @param email the given String corresponding to the email
     */
    @Override
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Set the phone number to be the given String
     * @param phoneNumber the given String corresponding to the phone number
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    /**
     * Set the city to be the given String
     * @param city the given String corresponding to the city
     */
    @Override
    public void setCity(String city) {this.city = city;}

    /**
     * Returns the User's username
     * @return The User's username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Returns the User's password
     * @return The User's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the User's email
     * @return The User's email
     */
    @Override
    public String getEmail(){
        return email;
    }

    /**
     * Returns the User's phone number
     * @return The User's phone number
     */
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the User's city
     * @return The User's city
     */
    @Override
    public String getCity() {
        return city;
    }

    /**
     * Returns the time at which the User was created
     * @return The time at which the User was created
     */
    @Override
    public LocalDateTime getCreationTime(){
        return ltd;
    }
}
