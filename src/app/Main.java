package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginUserDataAccessInterface;

import use_case.booksearch.*;
import interface_adapter.booksearch.*;
import view.*;
import data_access.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*Same as CA5 for now*/
/* public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject;

        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    } */


 public class Main {
    public static void main(String[] args) {
        // Real implementation of BookSearchDataAccessInterface
        BookSearchDataAccessInterface realDataAccess = new OpenLibraryDB();

        // Initialize the presenter and view model
        BookSearchViewModel viewModel = new BookSearchViewModel();
        BookSearchPresenter presenter = new BookSearchPresenter(viewModel);

        // Initialize the interactor (use case) with the real data access and presenter
        BookSearchInteractor interactor = new BookSearchInteractor(realDataAccess, presenter);

        // Initialize the controller with the interactor
        BookSearchController controller = new BookSearchController(interactor);

        // Initialize and display the BookSearchView
        BookSearchView view = new BookSearchView(controller, viewModel);
        view.display();
    }
}
