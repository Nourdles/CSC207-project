package use_case.login;

public class LoginOutputData {

    private final String username;
    private boolean useCaseFailed;

    /**
     * Create a new Login Output Data with the given parameters
     * @param username String that represents the username inputted by the User
     * @param useCaseFailed boolean that represents whether the use case failed
     */
    public LoginOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Returns the username stored in Output Data
     * @return String that represents the username stored in Output Data
     */
    public String getUsername() {
        return username;
    }
}
