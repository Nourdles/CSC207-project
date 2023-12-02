package app;

import interface_adapter.Listings.ListingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_listing.DeleteListingController;
import interface_adapter.delete_listing.DeleteListingPresenter;
import interface_adapter.delete_listing.DeleteListingViewModel;
import use_case.delete_listing.DeleteListingDataAccessInterface;
import use_case.delete_listing.DeleteListingInputBoundary;
import use_case.delete_listing.DeleteListingInteractor;
import use_case.delete_listing.DeleteListingOutputBoundary;
import view.ListingsView;

import javax.swing.*;
import java.io.IOException;

public class DeleteListingUseCaseFactory {

    private DeleteListingUseCaseFactory(){}

    public static ListingsView create(
            ViewManagerModel viewManagerModel,
            ListingsViewModel listingsViewModel,
            DeleteListingDataAccessInterface listingDataAccessObject,
            DeleteListingViewModel deleteListingViewModel
    ){
        try {
            DeleteListingController deleteListingController = createDeleteUseCase(viewManagerModel, listingsViewModel,
                    listingDataAccessObject, deleteListingViewModel);
            return new ListingsView(listingsViewModel, deleteListingController, viewManagerModel, deleteListingViewModel);
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
