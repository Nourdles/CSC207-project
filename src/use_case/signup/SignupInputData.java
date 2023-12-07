package use_case.signup;

import java.util.regex.*;

public class SignupInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String email;
    final private String phoneNumber;
    final private String city;

    /**
     * Create a new Signup Input Data with the given parameters
     * @param username String representing the username inputted by the User
     * @param password String representing the password inputted by the User
     * @param repeatPassword String representing the repeated password inputted by the User
     * @param email String representing the email inputted by the User
     * @param phoneNumber String representing the phone number inputted by the User
     * @param city String representing the city inputted by the User
     */
    public SignupInputData(String username, String password, String repeatPassword, String email, String phoneNumber, String city) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    /**
     * Returns the username stored in the current Input Data
     * @return String that represents the username stored in the current Input Data
     */
    String getUsername() {
        return username;
    }

    /**
     * Returns the email stored in the current Input Data
     * @return String that represents the email stored in the current Input Data
     */
    String getEmail(){
        return email;
    }

    /**
     * Returns the phone number stored in the current Input Data
     * @return String that represents the phone number stored in the current Input Data
     */
    String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * Returns the city stored in the current Input Data
     * @return String that represents the city stored in the current Input Data
     */
    String getCity(){
        return city;
    }

    /**
     * Returns the password stored in the current Input Data
     * @return String that represents the password stored in the current Input Data
     */
    String getPassword() {
        return password;
    }

    /**
     * Returns the repeated password stored in the current Input Data
     * @return String that represents the repeated password stored in the current Input Data
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Returns whether the password stored in Input Data fits the specified requirements
     * @param password String that represents the email inputted by a User
     * @return boolean indicating whether the password stored in Input Data fits the specified requirements
     */
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

    /**
     * Returns whether the email stored in Input Data fits the specified requirements
     * @param email String that represents the email inputted by a User
     * @return boolean indicating whether the email stored in Input Data fits the specified requirements
     */
    public boolean emailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);

        return !matcher.matches();
    }
}
