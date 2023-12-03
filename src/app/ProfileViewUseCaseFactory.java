package app;

import interface_adapter.Listings.ListingsController;
import interface_adapter.Listings.ListingsPresenter;
import interface_adapter.Listings.ListingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;
import use_case.listings.ListingsDataAccessInterface;
import use_case.listings.ListingsInputBoundary;
import use_case.listings.ListingsInteractor;
import use_case.listings.ListingsOutputBoundary;
import view.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class ProfileViewUseCaseFactory {
    private ProfileViewUseCaseFactory(){}
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
