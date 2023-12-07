package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.ListingFactory;
import interface_adapter.view_listings.ListingsController;
import interface_adapter.view_listings.ListingsPresenter;
import interface_adapter.view_listings.ListingsViewModel;
import interface_adapter.book_info.BookInfoController;
import interface_adapter.book_info.BookInfoPresenter;
import interface_adapter.book_info.BookInfoViewModel;
import interface_adapter.create_listing.CreateListingController;
import interface_adapter.create_listing.CreateListingPresenter;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.delete_listing.DeleteListingController;
import interface_adapter.delete_listing.DeleteListingPresenter;
import interface_adapter.delete_listing.DeleteListingViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search_filter.SearchFilterController;
import interface_adapter.search_filter.SearchFilterPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.book_info.BookInfoDataAccessInterface;
import use_case.book_info.BookInfoInteractor;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.create_listing.CreateListingInteractor;
import use_case.create_listing.CreateListingOutputBoundary;

import use_case.book_search.*;
import interface_adapter.book_search.*;
import use_case.delete_listing.DeleteListingInteractor;
import use_case.view_listings.ListingsInteractor;
import use_case.search_filter.SearchFilterInteractor;
import view.*;
import data_access.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

 public class Main {

    public static void main(String[] args) throws IOException {
        JFrame application = new JFrame("Login");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        BookSearchViewModel bookSearchViewModel = new BookSearchViewModel();
        BookInfoViewModel infoViewModel = new BookInfoViewModel();
        CreateListingViewModel createListingViewModel = new CreateListingViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        DeleteListingViewModel deleteListingViewModel = new DeleteListingViewModel();
        ListingsViewModel listingsViewModel = new ListingsViewModel();

        FileUserDataAccessObject userDataAccessObject;

        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileListingDataAccessObject fileListingDataAccessObject;
        try {
            fileListingDataAccessObject = new FileListingDataAccessObject("./listingInfo.csv", new ListingFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, bookSearchViewModel, userDataAccessObject, signupViewModel, createListingViewModel, listingsViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        ListingsPresenter listingsPresenter = new ListingsPresenter(viewManagerModel, listingsViewModel);
        ListingsInteractor listingsInteractor = new ListingsInteractor(fileListingDataAccessObject, listingsPresenter);
        ListingsController listingsController = new ListingsController(listingsInteractor);

        DeleteListingPresenter deleteListingPresenter = new DeleteListingPresenter(viewManagerModel, deleteListingViewModel);
        DeleteListingInteractor deleteListingInteractor = new DeleteListingInteractor(fileListingDataAccessObject, deleteListingPresenter);
        DeleteListingController deleteListingController = new DeleteListingController(deleteListingInteractor);

        ListingsProfileView listingsView = new ListingsProfileView(listingsViewModel, deleteListingController, viewManagerModel, deleteListingViewModel, profileViewModel);
        views.add(listingsView, listingsView.viewName);

        ProfileController profileController = new ProfileController();
        ProfileView profileView = new ProfileView(profileController, profileViewModel, listingsViewModel, listingsController, viewManagerModel);
        views.add(profileView, profileView.viewName);

        SearchFilterPresenter filterPresenter = new SearchFilterPresenter(bookSearchViewModel);

        BookSearchPresenter presenter = new BookSearchPresenter(bookSearchViewModel);
        BookSearchDataAccessInterface realDataAccess = new OpenLibraryDB();

        BookInfoPresenter infoPresenter = new BookInfoPresenter(infoViewModel, viewManagerModel);

        BookSearchInteractor interactor = new BookSearchInteractor(realDataAccess, presenter);
        SearchFilterInteractor filterInteractor = new SearchFilterInteractor(filterPresenter);

        BookInfoDataAccessInterface bookInfoDataAccessInterface = new FileListingDataAccessObject("./listingInfo.csv", new ListingFactory());
        BookInfoInteractor infoInteractor = new BookInfoInteractor(infoPresenter, bookInfoDataAccessInterface);

        BookSearchController bookSearchController = new BookSearchController(interactor);
        SearchFilterController searchFilterController = new SearchFilterController(filterInteractor);
        BookInfoController bookInfoController = new BookInfoController(infoInteractor);

        BookSearchView bookSearchView = new BookSearchView(bookSearchController, bookSearchViewModel, searchFilterController, bookInfoController, viewManagerModel);
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

            CreateListingView createListingView = new CreateListingView(createListingViewModel, createListingController, viewManagerModel, bookInfoController, infoViewModel);
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
