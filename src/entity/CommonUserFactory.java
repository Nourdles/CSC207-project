package entity;
public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     *
     * @param username a User's username.
     * @param password a User's password.
     * @param email a User's email address.
     * @param phoneNumber an int representing a User's phone Number.
     * @return User
     */

    /*Creating a CommonUser.  */
    public User create(String username, String password, String city, String email, int phoneNumber) {
        return new CommonUser(username, password, city, email, phoneNumber);

    }
}

