package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.booksearch.BookSearchViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            BookSearchViewModel bookSearchViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewModel
            ) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,
                    bookSearchViewModel, userDataAccessObject, signupViewModel);
            return new LoginView(loginViewModel, loginController, signupViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            BookSearchViewModel bookSearchViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewmodel
    ) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel,
                loginViewModel, bookSearchViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
