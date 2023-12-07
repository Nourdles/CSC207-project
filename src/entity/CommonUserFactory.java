package entity;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {

        /**
         * Creates a CommonUser with the given parameter
         * @param username username of the CommonUser
         * @param password password of the CommonUser
         * @param ltd creation time of the CommonUser
         * @param email email of the CommonUser
         * @param phoneNumber phone number of the CommonUser
         * @param city city of the CommonUser
         * @return A new CommonUser
         */
        @Override
        public User create(String username, String password, LocalDateTime ltd, String email, String phoneNumber, String city) {
        return new CommonUser(username, password, ltd, email, phoneNumber, city);

    }
}


