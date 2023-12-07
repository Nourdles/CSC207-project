package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.book_search.BookSearchState;
import interface_adapter.book_search.BookSearchViewModel;
import interface_adapter.create_listing.CreateListingState;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.view_listings.ListingsState;
import interface_adapter.view_listings.ListingsViewModel;
import org.junit.jupiter.api.Test;
import use_case.login.LoginOutputData;

import static org.junit.jupiter.api.Assertions.*;

class LoginPresenterTest {
    private final LoginViewModel loginViewModel = new LoginViewModel();
    private final LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
    private final BookSearchViewModel bookSearchViewModel = new BookSearchViewModel();
    private final CreateListingViewModel createListingViewModel = new CreateListingViewModel();
    private final ListingsViewModel listingsViewModel = new ListingsViewModel();
    private ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final LoginPresenter presenter = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel,
            bookSearchViewModel, createListingViewModel, listingsViewModel);

    @Test
    void prepareSuccessView() {
        LoginOutputData outputData = new LoginOutputData("username", false);
        presenter.prepareSuccessView(outputData);
        CreateListingState createListingState = createListingViewModel.getState();
        ListingsState listingsState = listingsViewModel.getState();
        BookSearchState currentState = bookSearchViewModel.getState();

        assertEquals("username", createListingState.getSeller());
        assertEquals("username", listingsState.getUsername());
        assertEquals(currentState, bookSearchViewModel.getState());
        assertEquals(listingsState, listingsViewModel.getState());
        assertEquals(createListingState, createListingViewModel.getState());
        assertEquals("book search", viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailView() {
        presenter.prepareFailView("error");
        LoginState loginState = loginViewModel.getState();
        assertEquals("error", loginState.getUsernameError());
    }
}