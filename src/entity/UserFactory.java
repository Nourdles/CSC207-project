package entity;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String username, String password, String email, String phoneNumber, String city);
}
