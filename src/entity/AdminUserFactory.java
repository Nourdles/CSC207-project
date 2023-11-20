package entity;

public class AdminUserFactory {
    /* Creating an AdminUser. */
    public User create(String username, String password, String email, int employeeNumber) {
        return new AdminUser(username, password, email, employeeNumber);
    }
}
