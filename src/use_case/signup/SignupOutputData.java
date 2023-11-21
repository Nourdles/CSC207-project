package use_case.signup;

public class SignupOutputData {

    private final String username;
    private boolean useCaseFailed;
    private String creationTime;

    public SignupOutputData(String username, String creationTIme, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.creationTime = creationTIme;
    }

    public String getUsername() {
        return username;
    }
    public String getCreationTime(){return creationTime;}
    public void setCreationTime(String creationTime){this.creationTime = creationTime;}

}
