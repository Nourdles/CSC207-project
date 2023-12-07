package interface_adapter.signup;

public class SignupState {
    private String email = "";
    private String phoneNumber;
    private String city = "";
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;

    /**
     * Create a new Signup State
     */
    public SignupState() {
    }

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
     * Returns the repeated password of the current State
     * @return String representing the repeated password of the current State
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Returns the email of the current State
     * @return String representing the email of the current State
     */
    public String getEmail(){return email;}

    /**
     * Returns the phone number of the current State
     * @return String representing the phone number of the current State
     */
    public String getPhoneNumber(){return phoneNumber;}

    /**
     * Returns the city of the current State
     * @return String representing the city of the current State
     */
    public String getCity(){return city;}

    /**
     * Sets the username of the current State to the given String
     * @param username String we want to set the username of the State to
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the username error of the current State to the given String
     * @param usernameError String we want to set the username error of the State to
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Sets the password of the current State to the given String
     * @param password String we want to set the password of the State to
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the repeated password of the current State to the given String
     * @param repeatPassword String we want to set the repeated password of the State to
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Sets the email of the current State to the given String
     * @param email String we want to set the email of the State to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the current State to the given String
     * @param phoneNumber String we want to set the phone number of the State to
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the city of the current State to the given String
     * @param city String we want to set the city of the State to
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns a String representation of the current State and all of its parameters
     * @return String with the current State and all of its parameters
     */
    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
