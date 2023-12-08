package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

import javax.swing.*;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class SignupUseCaseFactoryTest {
    private final ViewManagerModel viewManagerModelMock = mock(ViewManagerModel.class);
    private final LoginViewModel loginViewModelMock = mock(LoginViewModel.class);
    private final SignupViewModel signupViewModelMock = mock(SignupViewModel.class);
    private final SignupUserDataAccessInterface userDataAccessMock = mock(SignupUserDataAccessInterface.class);

    @Test
    void create() {
        SignupView resultView = SignupUseCaseFactory.create(viewManagerModelMock, loginViewModelMock,
                signupViewModelMock, userDataAccessMock);

        assertNotNull(resultView, "The returned SignupView should not be null");
        JOptionPane.showMessageDialog(null, "Could not open user data file.");
    }
}