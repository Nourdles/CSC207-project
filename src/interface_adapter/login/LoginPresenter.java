package interface_adapter.login;

import interface_adapter.view_listings.ListingsState;
import interface_adapter.view_listings.ListingsViewModel;
import interface_adapter.create_listing.CreateListingState;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.book_search.*;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final BookSearchViewModel bookSearchViewModel;
    private final CreateListingViewModel createListingViewModel;
    private final ListingsViewModel listingsViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Creates a new Login Presenter with the given parameters
     *
     * @param viewManagerModel       View Manager Model
     * @param loggedInViewModel      Logged In View Model
     * @param loginViewModel         Login View Model
     * @param bookSearchViewModel    Book Search View Model
     * @param createListingViewModel Create Listing View Model
     * @param listingsViewModel      Listings View Model
     */
    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          BookSearchViewModel bookSearchViewModel, CreateListingViewModel createListingViewModel, ListingsViewModel listingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.bookSearchViewModel = bookSearchViewModel;
        this.createListingViewModel = createListingViewModel;
        this.listingsViewModel = listingsViewModel;
    }

    /**
     * Provided no errors have occurred elsewhere in the use case, updates the Create Listing, Listings, and Book Search
     * State with the currently logged-in User, and switches the view to Book Search View
     *
     * @param response Login Output Data
     */
    @Override
    public void prepareSuccessView(LoginOutputData response) {
        CreateListingState createListingState = createListingViewModel.getState();
        createListingState.setSeller(response.getUsername());
        createListingViewModel.setState(createListingState);
        createListingViewModel.firePropertyChanged();

        ListingsState listingsState = listingsViewModel.getState();
        listingsState.setUsername(response.getUsername());
        listingsViewModel.setState(listingsState);
        listingsViewModel.firePropertyChanged();

        BookSearchState currentState = bookSearchViewModel.getState();
        bookSearchViewModel.setState(currentState);
        bookSearchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(bookSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Provided an error occurred somewhere in the use case, sets the Login State to an error state
     *
     * @param error String representing the error that occurred
     */
    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}