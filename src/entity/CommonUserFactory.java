package entity;
public class CommonUserFactory implements UserFactory {
        /**
         * Requires: password is valid.
         * @param username
         * @param password
         * @param email
         * @param phoneNumber
         * @return
         */

    /*Creating a CommonUser.  */
    public User create(String username, String password, String city, String email, int phoneNumber) {
        return new CommonUser(username, password, city, email, phoneNumber);

    }


