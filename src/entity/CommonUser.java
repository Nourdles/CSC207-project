package entity;

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

    /**
     * A CommonUser of the system: They can browse others' listings or create/delete their own.
     * @param username username of the CommonUser
     * @param password password of the CommonUser
     * @param creationTime time the CommonUser was created
     * @param email email of the CommonUser
     * @param phoneNumber phone number of the CommonUser
     * @param city city of the CommonUser
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

    /**
     * Returns the username of the CommonUser
     * @return The username of the CommonUser
     */
    @Override
    public String getUsername(){
        return username;
    }

    /**
     * Returns the password of the CommonUser
     * @return The password of the CommonUser
     */
    @Override
    public String getPassword(){
        return password;
    }

    /**
     * Returns the email of the CommonUser
     * @return The email of the CommonUser
     */
    @Override
    public String getEmail(){
        return email;
    }

    /**
     * Returns the phone number of the CommonUser
     * @return The phone number of the CommonUser
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * Returns the city of the CommonUser
     * @return The city of the CommonUser
     */
    public String getCity(){
        return city;
    }

    /**
     * Returns the time the CommonUser was created
     * @return The time the CommonUser was created
     */
    @Override
    public LocalDateTime getCreationTime(){
        return creationTime;
    }

    /**
     * Sets the CommonUser's username to the given String
     * @param username String we want to set the CommonUser's username to
     */
    @Override
    public void setUsername(String username) {this.username = username;}

    /**
     * Sets the CommonUser's password to the given String
     * @param password String we want to set the CommonUser's password to
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the CommonUser's email to the given String
     * @param email String we want to set the CommonUser's email to
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the CommonUser's phone number to the given String
     * @param phoneNumber String we want to set the CommonUser's phone number to
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the CommonUser's city to the given String
     * @param city String we want to set the CommonUser's city to
     */
    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof User)){
            return false;
        }
        CommonUser user = (CommonUser) obj;
        double performDifference = user.performanceRating - this.performanceRating;
        return (user.username.equals(this.username) && user.password.equals(this.password) &&
                user.email.equals(this.email)&& user.phoneNumber.equals(this.phoneNumber) &&
                user.city.equals(this.city) && user.creationTime.equals(this.creationTime) &&
                user.numRatings == this.numRatings && performDifference < 0.00001);
    }
}
