package app;

import interface_adapter.view_listings.ListingsController;
import interface_adapter.view_listings.ListingsPresenter;
import interface_adapter.view_listings.ListingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;
import use_case.view_listings.ListingsDataAccessInterface;
import use_case.view_listings.ListingsInputBoundary;
import use_case.view_listings.ListingsInteractor;
import use_case.view_listings.ListingsOutputBoundary;
import view.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class ProfileViewUseCaseFactory {
    private ProfileViewUseCaseFactory(){}

    /**
     * Returns a View where a User can click on a button to view their listings
     * @param viewModel the view model for the profile view
     * @param viewManagerModel
     * @param listingDataAccessObject
     * @param listingsViewModel the view model for the view where the currently logged in User can view their listings
     * @return A View where a User can click on a button to view their listings
     */
    public static ProfileView create(
            ProfileViewModel viewModel,
            ViewManagerModel viewManagerModel,
            ListingsDataAccessInterface listingDataAccessObject,
            ListingsViewModel listingsViewModel

    ){
        try {
            ListingsController listingsController = createListingsUseCase(viewManagerModel, listingsViewModel, listingDataAccessObject);

            return new ProfileView(new ProfileController(), viewModel, listingsViewModel, listingsController,viewManagerModel);
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open user account file");
        }
        return null;
    }
    private static ListingsController createListingsUseCase(ViewManagerModel viewManagerModel,
                                                            ListingsViewModel listingsViewModel,
                                                            ListingsDataAccessInterface listingDataAccessObject)
            throws IOException{
        ListingsOutputBoundary listingsOutputBoundary = new ListingsPresenter(viewManagerModel,listingsViewModel);
        ListingsInputBoundary listingsInputBoundary = new ListingsInteractor(listingDataAccessObject, listingsOutputBoundary);
        return new ListingsController(listingsInputBoundary);
    }
}
