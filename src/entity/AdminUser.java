package entity;

import entity.User;
public class AdminUser implements User {
    private String username;
    private String password;

    private String email;

    AdminUser(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
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

    public void setEmail(String email){
        this.email = email;
    }

}
