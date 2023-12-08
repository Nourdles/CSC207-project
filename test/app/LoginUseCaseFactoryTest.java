package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.book_search.BookSearchViewModel;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_listings.ListingsViewModel;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class LoginUseCaseFactoryTest {
    private final ViewManagerModel viewManagerModelMock = mock(ViewManagerModel.class);
    private final LoginViewModel loginViewModelMock = mock(LoginViewModel.class);
    private final LoggedInViewModel loggedInViewModelMock = mock(LoggedInViewModel.class);
    private final BookSearchViewModel bookSearchViewModelMock = mock(BookSearchViewModel.class);
    private final LoginUserDataAccessInterface userDataAccessMock = mock(LoginUserDataAccessInterface.class);
    private final SignupViewModel signupViewModelMock = mock(SignupViewModel.class);
    private final CreateListingViewModel createListingViewModelMock = mock(CreateListingViewModel.class);
    private final ListingsViewModel listingsViewModelMock = mock(ListingsViewModel.class);

    @Test
    void create() {
        LoginView resultView = LoginUseCaseFactory.create(viewManagerModelMock, loginViewModelMock,
                loggedInViewModelMock, bookSearchViewModelMock,
                userDataAccessMock, signupViewModelMock,
                createListingViewModelMock, listingsViewModelMock);

        assertNotNull(resultView, "The returned LoginView should not be null");
        JOptionPane.showMessageDialog(null, "Could not open user data file.");
    }
}