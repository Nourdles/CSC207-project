package entity;

import java.time.LocalDateTime;

public interface User {
    String getUsername();
    String getPassword();
    String getEmail();
    String getPhoneNumber();
    String getCity();
    LocalDateTime getCreationTime();
    void setPassword(String password);
    void setEmail(String email);
    void setPhoneNumber(String phoneNumber);
    void setCity(String city);

}
