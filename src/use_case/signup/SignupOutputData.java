package use_case.signup;

public class SignupOutputData {

    private final String username;
    private final boolean useCaseFailed;
    private String creationTime;

    /**
     * Creates a new Signup Output Data with the given parameters
     * @param username String that represents the username of the User that has been created
     * @param creationTime String that represents the creation time of the new User
     * @param useCaseFailed Boolean that represents whether the use case failed
     */
    public SignupOutputData(String username, String creationTime, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.creationTime = creationTime;
    }

    /**
     * Returns the username of the User that was created in Output Data
     * @return String that represents the username of the User that was created in Output Data
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the creation time of the new User stored in Output Data
     * @return String that represents the creation time of the new User stored in Output Data
     */
    public String getCreationTime(){return creationTime;}

    /**
     * Set the time of creation of the new User stored in Output Data to the given String
     * @param creationTime String we want to set the creation time of the new User to
     */
    public void setCreationTime(String creationTime){this.creationTime = creationTime;}
}
