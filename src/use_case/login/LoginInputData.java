package use_case.login;

public class LoginInputData {

    final private String username;
    final private String password;

    /**
     * Creates a new Login Input Data with the given parameters
     * @param username String that represents the username inputted by the User
     * @param password String that represents the password inputted by the User
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username stored in Input Data
     * @return String that represents the username stored in Input Data
     */
    String getUsername() {
        return username;
    }

    /**
     * Returns the password stored in Input Data
     * @return String that represents the password stored in Input Data
     */
    String getPassword() {
        return password;
    }
}
