package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Listing;
import entity.ListingFactory;
import interface_adapter.create_listing.CreateListingController;
import interface_adapter.create_listing.CreateListingPresenter;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searchfilter.SearchFilterController;
import interface_adapter.searchfilter.SearchFilterPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.create_listing.CreateListingInteractor;
import use_case.create_listing.CreateListingOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;

import use_case.booksearch.*;
import interface_adapter.booksearch.*;
import use_case.searchfilter.SearchFilterInteractor;
import view.*;
import data_access.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

 public class Main {

    public static void main(String[] args) throws IOException {
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

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        BookSearchViewModel bookSearchViewModel = new BookSearchViewModel();
        CreateListingViewModel createListingViewModel = new CreateListingViewModel();

        FileUserDataAccessObject userDataAccessObject;

        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileListingDataAccessObject fileListingDataAccessObject;



        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, bookSearchViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        SearchFilterPresenter filterPresenter = new SearchFilterPresenter(bookSearchViewModel);

        BookSearchPresenter presenter = new BookSearchPresenter(bookSearchViewModel);
        BookSearchDataAccessInterface realDataAccess = new OpenLibraryDB();

        // Initialize the interactor (use case) with the real data access and presenter
        BookSearchInteractor interactor = new BookSearchInteractor(realDataAccess, presenter);
        SearchFilterInteractor filterInteractor = new SearchFilterInteractor(filterPresenter);

        // Initialize the controller with the interactor
        BookSearchController bookSearchController = new BookSearchController(interactor);
        SearchFilterController searchFilterController = new SearchFilterController(filterInteractor);

        BookSearchView bookSearchView = new BookSearchView(bookSearchController, bookSearchViewModel, searchFilterController);
        views.add(bookSearchView, bookSearchView.viewName);


        CreateListingOutputBoundary createListingPresenter = new CreateListingPresenter(viewManagerModel, createListingViewModel);
        ListingFactory listingFactory = new ListingFactory();

        try {
            CreateListingDataAccessInterface createListingDataAccessInterface = new FileListingDataAccessObject("./listingInfo.csv", new ListingFactory());
            CreateListingInteractor createListingInteractor = new CreateListingInteractor(createListingDataAccessInterface,
                    createListingPresenter, listingFactory);

            CreateListingController createListingController = new CreateListingController(createListingInteractor);

            CreateListingView createListingView = new CreateListingView(createListingViewModel, createListingController);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

}
