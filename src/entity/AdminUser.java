package entity;
import entity.User;

import java.time.LocalDateTime;

public class AdminUser implements User {
    private String username;
    private String password;

    private String email;
    private final int employeeNumber;

    AdminUser(String username, String password, String email, int employeeNumber){
        this.username = username;
        this.password = password;
        this.email = email;
        this.employeeNumber = employeeNumber;
    }

    /*Could add change username, password, email functionalities or use cases */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public String getCity() {
        return null;
    }
    @Override
    public LocalDateTime getCreationTime(){
        return null;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
