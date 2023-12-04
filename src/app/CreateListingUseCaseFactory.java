package app;

import entity.ListingFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_listing.CreateListingController;
import interface_adapter.create_listing.CreateListingPresenter;
import interface_adapter.create_listing.CreateListingViewModel;
import interface_adapter.book_info.*;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.create_listing.CreateListingInputBoundary;
import use_case.create_listing.CreateListingInteractor;
import use_case.create_listing.CreateListingOutputBoundary;
import view.CreateListingView;
//import view.ListingsView;

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
    public static CreateListingView create(ViewManagerModel viewManagerModel, CreateListingViewModel createListingViewModel,
                                           CreateListingDataAccessInterface fileListingDataAccessObject, BookInfoViewModel bookInfoViewModel, BookInfoController bookInfoController){
        try {
            CreateListingController createListingController = createCreateListingUseCase(viewManagerModel,
                    createListingViewModel, bookInfoViewModel, fileListingDataAccessObject);

            return new CreateListingView(createListingViewModel, createListingController, viewManagerModel, bookInfoController, bookInfoViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open listings data file.");
        }
        return null;
    }

    private static CreateListingController createCreateListingUseCase(ViewManagerModel viewManagerModel,
                                                                      CreateListingViewModel createListingViewModel,
                                                                      BookInfoViewModel bookInfoViewModel,
                                                                      CreateListingDataAccessInterface fileListingDataAccessObject) throws IOException {
        CreateListingOutputBoundary createListingOutputBoundary = new CreateListingPresenter(viewManagerModel, createListingViewModel, bookInfoViewModel);

        ListingFactory listingFactory = new ListingFactory();

        CreateListingInputBoundary createListingInteractor = new CreateListingInteractor(
                fileListingDataAccessObject, createListingOutputBoundary, listingFactory);

        return new CreateListingController(createListingInteractor);
    }

}
