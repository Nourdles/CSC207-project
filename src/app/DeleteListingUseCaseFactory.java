package app;

import interface_adapter.view_listings.ListingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_listing.DeleteListingController;
import interface_adapter.delete_listing.DeleteListingPresenter;
import interface_adapter.delete_listing.DeleteListingViewModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.delete_listing.DeleteListingDataAccessInterface;
import use_case.delete_listing.DeleteListingInputBoundary;
import use_case.delete_listing.DeleteListingInteractor;
import use_case.delete_listing.DeleteListingOutputBoundary;
import use_case.view_listings.ListingsDataAccessInterface;
import view.ListingsProfileView;

import javax.swing.*;
import java.io.IOException;

public class DeleteListingUseCaseFactory {

    private DeleteListingUseCaseFactory(){}

    /** Return a View of a User's Listings on their profile
     * @param viewManagerModel
     * @param listingsViewModel
     * @param listingDataAccessObject
     * @param deleteListingDataAccessObject DAO in charge of deleting listings from the csv file they are stored in
     * @param deleteListingViewModel
     * @param profileViewModel
     * @return A View of a User's listings on their profile
     */
    public static ListingsProfileView create(
            ViewManagerModel viewManagerModel,
            ListingsViewModel listingsViewModel,
            ListingsDataAccessInterface listingDataAccessObject,
            DeleteListingDataAccessInterface deleteListingDataAccessObject,
            DeleteListingViewModel deleteListingViewModel,
            ProfileViewModel profileViewModel
    ){
        try {
//            ListingsController listingsController = createListingsUseCase(viewManagerModel, listingsViewModel, listingDataAccessObject);
            DeleteListingController deleteListingController = createDeleteUseCase(viewManagerModel, listingsViewModel,
                    deleteListingDataAccessObject, deleteListingViewModel);
            return new ListingsProfileView(listingsViewModel, deleteListingController, viewManagerModel, deleteListingViewModel, profileViewModel);
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not find user listings data");
        }
        return null;
    }
    private static DeleteListingController createDeleteUseCase(
            ViewManagerModel viewManagerModel,
            ListingsViewModel listingsViewModel,
            DeleteListingDataAccessInterface listingDataAccessInterface,
            DeleteListingViewModel deleteListingViewModel
    )throws IOException{
        DeleteListingOutputBoundary deleteListingOutputBoundary = new DeleteListingPresenter(viewManagerModel, deleteListingViewModel);

        DeleteListingInputBoundary deleteListingInteractor = new DeleteListingInteractor(listingDataAccessInterface, deleteListingOutputBoundary);

        return new DeleteListingController(deleteListingInteractor);
    }
}
