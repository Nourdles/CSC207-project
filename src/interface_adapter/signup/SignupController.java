package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {
    final SignupInputBoundary userSignupUseCaseInteractor;

    /**
     * Create a new Signup Controller with the given parameter
     * @param userSignupUseCaseInteractor Sign Up Input Boundary
     */
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Creates a new Input Data for the Signup use case with the given parameters, and calls the execute method of the
     * Interactor
     * @param username String representing the username entered by the User
     * @param password1 String representing the password entered by the User
     * @param password2 String representing the repeated password entered by the User
     * @param email String representing the email entered by the User
     * @param phoneNumber String representing the phone number entered by the User
     * @param city String representing the city entered by the User
     */
    public void execute(String username, String password1, String password2,
                      String email, String phoneNumber, String city) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2,
                email, phoneNumber, city);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
