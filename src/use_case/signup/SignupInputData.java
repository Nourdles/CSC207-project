package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String email;
    final private int phoneNumber;
    final private String city;

    public SignupInputData(String username, String password, String repeatPassword, String city, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    String getUsername() {
        return username;
    }
    String getEmail(){
        return email;
    }
    int getPhoneNumber(){
        return phoneNumber;
    }
    String getCity(){
        return city;
    }

    String getPassword() {
        return password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }

}
