package app;

import entity.CommonUserFactory;
import entity.ListingFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_listing.CreateListingController;
import interface_adapter.create_listing.CreateListingPresenter;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.signup.SignupController;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.create_listing.CreateListingInputBoundary;
import use_case.create_listing.CreateListingInteractor;
import use_case.create_listing.CreateListingOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.ListingsView;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class CreateListingUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private CreateListingUseCaseFactory(){}

    /** Return a View of a User's listings after an attempt to create a listing.
     *
     * @param viewManagerModel
     * @param createListingViewModel
     * @param fileListingDataAccessObject
     * @return A View of a User's listings after a user has attempted to create a listing.
     */
    public static ListingsView create(ViewManagerModel viewManagerModel, CreateListingViewModel createListingViewModel,
                                      CreateListingDataAccessInterface fileListingDataAccessObject){
        try {
            CreateListingController createListingController = createCreateListingUseCase(viewManagerModel,
                    createListingViewModel, fileListingDataAccessObject);

            return new ListingsView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open listings data file.");
        }
        return null;
    }

    private static CreateListingController createCreateListingUseCase(ViewManagerModel viewManagerModel,
                                                                      CreateListingViewModel createListingViewModel,
                                                                      CreateListingDataAccessInterface fileListingDataAccessObject) throws IOException {
        CreateListingOutputBoundary createListingOutputBoundary = new CreateListingPresenter(viewManagerModel, createListingViewModel);

        ListingFactory listingFactory = new ListingFactory();

        CreateListingInputBoundary createListingInteractor = new CreateListingInteractor(
                fileListingDataAccessObject, createListingOutputBoundary, listingFactory);

        return new CreateListingController(createListingInteractor);
    }

}
