package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";

    /**
     * Create a new Logged In State
     */
    public LoggedInState() {}

    /**
     * Returns the username of the current State
     * @return String representing the username of the current State
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the current State to the given String
     * @param username String we want to set the username of the current State to
     */
    public void setUsername(String username) {
        this.username = username;
    }
}