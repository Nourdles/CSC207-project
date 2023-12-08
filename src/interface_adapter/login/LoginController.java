package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;

    /**
     * Create a new Login Controller with the given parameter
     * @param loginUseCaseInteractor Login Input Boundary
     */
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Creates a new Input Data for the Login use case and calls the execute method of the Interactor
     * @param username String that represents the username entered by the User
     * @param password String that represents the password entered by the User
     */
    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
