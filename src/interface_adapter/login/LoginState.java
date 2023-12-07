package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;

    /**
     * Create a new Login State
     */
    public LoginState() {}

    /**
     * Returns the username of the current State
     * @return String representing the username of the current State
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the username error of the current State
     * @return String representing the username error of the current State
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Returns the password of the current State
     * @return String representing the password of the current State
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the password error of the current State
     * @return String representing the password error of the current State
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Set the username of the current State to be the given String
     * @param username String we want to set the username of the current State to
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the username error of the current State to be the given String
     * @param usernameError String we want to set the username error of the current State to
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Set the password of the current State to be the given String
     * @param password String we want to set the password of the current State to
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
