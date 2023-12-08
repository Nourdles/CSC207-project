package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.book_info.BookInfoController;
import interface_adapter.book_info.BookInfoViewModel;
import interface_adapter.create_listing.CreateListingViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.create_listing.CreateListingDataAccessInterface;
import view.CreateListingView;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CreateListingUseCaseFactoryTest {
    private final ViewManagerModel viewManagerModelMock = mock(ViewManagerModel.class);
    private final CreateListingViewModel createListingViewModelMock = mock(CreateListingViewModel.class);
    private final CreateListingDataAccessInterface dataAccessMock = mock(CreateListingDataAccessInterface.class);
    private final BookInfoViewModel bookInfoViewModelMock = mock(BookInfoViewModel.class);
    private final BookInfoController bookInfoControllerMock = mock(BookInfoController.class);

    @Test
    void create() {
        CreateListingView resultView = CreateListingUseCaseFactory.create(viewManagerModelMock, createListingViewModelMock,
                dataAccessMock, bookInfoViewModelMock, bookInfoControllerMock);
        assertNotNull(resultView, "The returned CreateListingView should not be null");
        JOptionPane.showMessageDialog(null, "Could not open listings data file.");
    }
}