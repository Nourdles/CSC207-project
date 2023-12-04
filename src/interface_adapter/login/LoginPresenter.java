package interface_adapter.login;

import interface_adapter.create_listing.CreateListingState;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.booksearch.*;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final BookSearchViewModel bookSearchViewModel;
    private final CreateListingViewModel createListingViewModel;

    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          BookSearchViewModel bookSearchViewModel, CreateListingViewModel createListingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.bookSearchViewModel = bookSearchViewModel;
        this.createListingViewModel = createListingViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        CreateListingState createListingState = createListingViewModel.getState();
        createListingState.setSeller(response.getUsername());
        createListingViewModel.setState(createListingState);
        createListingViewModel.firePropertyChanged();

        BookSearchState currentState = bookSearchViewModel.getState();
        // Assuming BookSearchState can store a username or other relevant information
        // currentState.setUsername(response.getUsername());
        bookSearchViewModel.setState(currentState);
        bookSearchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(bookSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
