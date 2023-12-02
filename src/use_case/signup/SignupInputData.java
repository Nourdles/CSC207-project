package use_case.signup;

import java.util.regex.*;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String email;
    final private String phoneNumber;
    final private String city;

    public SignupInputData(String username, String password, String repeatPassword, String email, String phoneNumber, String city) {
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
    String getPhoneNumber(){
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
    public boolean passwordMeetsReq(String password){
        if (password.length() <6){
            return false;
        }else {
            boolean checkLower = false;
            boolean checkUpper = false;
            boolean checkNumber = false;
            for (char c: password.toCharArray()){
                if (c == ' '){
                    return false;
                } else if (Character.isLowerCase(c)) {
                    checkLower = true;
                } else if (Character.isUpperCase(c)) {
                    checkUpper = true;
                } else if (Character.isDigit(c)) {
                    checkNumber = true;
                }
            }
            return (checkLower && checkNumber && checkUpper);
        }
    }
    public boolean emailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);

        boolean isMatch = !matcher.matches();
        return isMatch;
    }


}
