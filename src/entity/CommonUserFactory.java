package entity;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
        /** A factory to create a Commonuser.
         * Requires: password is valid.
         * @param username
         * @param password
         * @param email
         * @param phoneNumber
         * @param city
         * @return a new CommonUser
         */

    /*Creating a CommonUser.  */
        @Override
        public User create(String username, String password, LocalDateTime ltd, String email, String phoneNumber, String city) {
        return new CommonUser(username, password, ltd, email, phoneNumber, city);

    }
}


