package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.view_listings.ListingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.book_search.BookSearchViewModel;
import interface_adapter.create_listing.CreateListingViewModel;
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

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    /** Return a View where a User can input their information to log in.
     *
     * @param viewManagerModel
     * @param createListingViewModel
     * @param loginViewModel
     * @param loggedInViewModel
     * @param bookSearchViewModel the view model of the view that is switched to if the log in is successful
     * @param userDataAccessObject the DAO in charge of reading the csv file where user info is stored to see if the
     *                             user exists and their password is correct
     * @param signupViewModel the view model for the view that is switched to if the User clicks the "sign up" button
     * @param createListingViewModel the view model to create a listing (needed here to retrieve the username of the
     *                               user currently logged in)
     * @param listingsViewModel the view model to view a User's listings on their profile (needed here to keep track of
     *                          which user is signed in)
     * @return View where a User can input their information to log in.
     */
    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            BookSearchViewModel bookSearchViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewModel,
            CreateListingViewModel createListingViewModel,
            ListingsViewModel listingsViewModel
            ) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,
                    bookSearchViewModel, userDataAccessObject, signupViewModel, createListingViewModel, listingsViewModel);
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
            SignupViewModel signupViewmodel,
            CreateListingViewModel createListingViewModel,
            ListingsViewModel listingsViewModel
    ) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel,
                loginViewModel, bookSearchViewModel, createListingViewModel, listingsViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
