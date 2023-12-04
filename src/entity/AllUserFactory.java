package entity;

import java.time.LocalDateTime;

public class AllUserFactory implements UserFactory {
        /**
         * Requires: password is valid.
         * @param username
         * @param password
         * @param email
         * @param phoneNumber
         * @param city
         * @return
         */

    /*Creating a CommonUser.  */
        @Override
        public User create(String username, String password, LocalDateTime ltd, String email, String phoneNumber, String city) {
        return new CommonUser(username, password, ltd, email, phoneNumber, city);

    }
}


