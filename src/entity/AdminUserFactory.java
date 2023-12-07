package entity;

import java.time.LocalDateTime;

public class AdminUserFactory implements UserFactory {

    /**
     * Creates a new Admin User with the given parameter
     * @param username username of the Admin
     * @param password password of the Admin
     * @param ltd creation time of the Admin
     * @param email email of the Admin
     * @param phoneNumber phone number of the Admin
     * @param city city of the Admin
     * @return An Admin User
     */
    @Override
    public User create(String username, String password, LocalDateTime ltd, String email, String phoneNumber, String city) {
        return new AdminUser(username, password, ltd, email, phoneNumber, city);
    }
}


