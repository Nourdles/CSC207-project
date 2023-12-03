package app;

import interface_adapter.Listings.ListingsController;
import interface_adapter.Listings.ListingsPresenter;
import interface_adapter.Listings.ListingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_listing.DeleteListingController;
import interface_adapter.delete_listing.DeleteListingPresenter;
import interface_adapter.delete_listing.DeleteListingViewModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.delete_listing.DeleteListingDataAccessInterface;
import use_case.delete_listing.DeleteListingInputBoundary;
import use_case.delete_listing.DeleteListingInteractor;
import use_case.delete_listing.DeleteListingOutputBoundary;
import use_case.listings.ListingsDataAccessInterface;
import use_case.listings.ListingsInputBoundary;
import use_case.listings.ListingsInteractor;
import use_case.listings.ListingsOutputBoundary;
import view.ListingsProfileView;

import javax.swing.*;
import java.io.IOException;

public class DeleteListingUseCaseFactory {

    private DeleteListingUseCaseFactory(){}

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
//    private static ListingsController createListingsUseCase(
//            ViewManagerModel viewManagerModel,
//            ListingsViewModel listingsViewModel,
//            ListingsDataAccessInterface listingsDataAccessInterface
//    )throws IOException{
//        ListingsOutputBoundary listingsOutputBoundary = new ListingsPresenter(viewManagerModel, listingsViewModel
//                );
//        ListingsInputBoundary listingsInputBoundary = new ListingsInteractor(listingsDataAccessInterface, listingsOutputBoundary);
//        return new ListingsController(listingsInputBoundary);
//    }
}
