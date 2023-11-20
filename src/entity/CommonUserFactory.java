package entity;
public class CommonUserFactory implements UserFactory {
        /**
         * Requires: password is valid.
         * @param name
         * @param password
         * @param email
         * @param phoneNumber
         * @param city
         * @return
         */
    @Override
    public User create(String name, String password, String email, String phoneNumber, String city) {
        return new CommonUser(name, password, email, phoneNumber, city);
    }
}


