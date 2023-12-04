package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.ListingFactory;
import interface_adapter.book_info.BookInfoController;
import interface_adapter.book_info.BookInfoPresenter;
import interface_adapter.book_info.BookInfoViewModel;
import interface_adapter.create_listing.CreateListingController;
import interface_adapter.create_listing.CreateListingPresenter;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searchfilter.SearchFilterController;
import interface_adapter.searchfilter.SearchFilterPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.book_info.BookInfoDataAccessInterface;
import use_case.book_info.BookInfoInteractor;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.create_listing.CreateListingInteractor;
import use_case.create_listing.CreateListingOutputBoundary;

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
        BookInfoViewModel infoViewModel = new BookInfoViewModel();
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

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, bookSearchViewModel, userDataAccessObject, signupViewModel, createListingViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        SearchFilterPresenter filterPresenter = new SearchFilterPresenter(bookSearchViewModel);

        BookSearchPresenter presenter = new BookSearchPresenter(bookSearchViewModel);
        BookSearchDataAccessInterface realDataAccess = new OpenLibraryDB();

        BookInfoPresenter infoPresenter = new BookInfoPresenter(infoViewModel, viewManagerModel);

        // Initialize the interactor (use case) with the real data access and presenter
        BookSearchInteractor interactor = new BookSearchInteractor(realDataAccess, presenter);
        SearchFilterInteractor filterInteractor = new SearchFilterInteractor(filterPresenter);

        BookInfoDataAccessInterface bookInfoDataAccessInterface = new FileListingDataAccessObject("./listingInfo.csv", new ListingFactory());
        BookInfoInteractor infoInteractor = new BookInfoInteractor(infoPresenter, bookInfoDataAccessInterface);

        // Initialize the controller with the interactor
        BookSearchController bookSearchController = new BookSearchController(interactor);
        SearchFilterController searchFilterController = new SearchFilterController(filterInteractor);
        BookInfoController bookInfoController = new BookInfoController(infoInteractor);

        BookSearchView bookSearchView = new BookSearchView(bookSearchController, bookSearchViewModel, searchFilterController, bookInfoController);
        views.add(bookSearchView, bookSearchView.viewName);


        CreateListingOutputBoundary createListingPresenter = new CreateListingPresenter(viewManagerModel, createListingViewModel, infoViewModel);
        ListingFactory listingFactory = new ListingFactory();

        try {
            CreateListingDataAccessInterface createListingDataAccessInterface = new FileListingDataAccessObject("./listingInfo.csv", new ListingFactory());
            CreateListingInteractor createListingInteractor = new CreateListingInteractor(createListingDataAccessInterface,
                    createListingPresenter, listingFactory);

            CreateListingController createListingController = new CreateListingController(createListingInteractor);
            BookInfoView bookInfoView = new BookInfoView(infoViewModel, viewManagerModel, createListingViewModel, createListingController);
            views.add(bookInfoView, bookInfoView.viewName);

            CreateListingView createListingView = new CreateListingView(createListingViewModel, createListingController, viewManagerModel);
            views.add(createListingView, createListingView.viewName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

}
