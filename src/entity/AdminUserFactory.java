package entity;

import java.time.LocalDateTime;

public class AdminUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param username
     * @param password
     * @param email
     * @param phoneNumber
     * @param city
     * @return an AdminUser
     */

    /*Creating an AdminUser.  */
    @Override
    public User create(String username, String password, LocalDateTime ltd, String email, String phoneNumber, String city) {
        return new AdminUser(username, password, ltd, email, phoneNumber, city);
    }
}


