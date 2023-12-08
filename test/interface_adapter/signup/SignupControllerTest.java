package interface_adapter.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.*;

import static org.junit.jupiter.api.Assertions.*;

class SignupControllerTest {
    private SignupController controller;

    @Test
    void execute() {
        SignupUserDataAccessInterface DAO = new InMemoryUserDataAccessObject();
        ViewManagerModel model = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupOutputBoundary outputBoundary = new SignupPresenter(model, signupViewModel, loginViewModel);
        SignupInputBoundary inputBoundary = new SignupInteractor(DAO,outputBoundary, new CommonUserFactory());
        controller = new SignupController(inputBoundary);
        controller.execute("username", "password", "password", "user@mail.com", "123",
                "Toronto");

        SignupInputData inputData = new SignupInputData("username", "password", "password",
                "user@mail.com", "123", "Toronto");
    }
}