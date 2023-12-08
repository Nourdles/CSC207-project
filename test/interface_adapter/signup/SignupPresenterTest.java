package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupOutputData;

import static org.junit.jupiter.api.Assertions.*;

class SignupPresenterTest {
    private ViewManagerModel model = new ViewManagerModel();
    private SignupViewModel signupViewModel = new SignupViewModel();
    private LoginViewModel loginViewModel = new LoginViewModel();
    private SignupPresenter presenter = new SignupPresenter(model, signupViewModel, loginViewModel);

    @Test
    void prepareSuccessView() {
        SignupOutputData outputData = new SignupOutputData("username", "2023-11-29T22:53:04.255264",
                false);
        presenter.prepareSuccessView(outputData);
        LoginState loginState = loginViewModel.getState();
        assertEquals("log in", model.getActiveView());
        assertEquals("username", loginState.getUsername());
        assertEquals(loginState, loginViewModel.getState());
    }

    @Test
    void prepareFailView() {
        presenter.prepareFailView("error message");
        SignupState signupState = signupViewModel.getState();
        assertEquals("error message", signupState.getUsernameError());
    }
}