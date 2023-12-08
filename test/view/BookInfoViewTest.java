package view;

import data_access.FileListingDataAccessObject;
import entity.ListingFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.book_info.BookInfoState;
import interface_adapter.book_info.BookInfoViewModel;
import interface_adapter.create_listing.CreateListingController;
import interface_adapter.create_listing.CreateListingPresenter;
import interface_adapter.create_listing.CreateListingViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.create_listing.CreateListingDataAccessInterface;
import use_case.create_listing.CreateListingInputBoundary;
import use_case.create_listing.CreateListingInputData;
import use_case.create_listing.CreateListingInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookInfoViewTest {
    private String listingsFilePath;
    private File listingInfo;
    private ListingFactory listingFactory;
    private BookInfoViewModel bookViewModel;
    private ViewManagerModel model;
    private CreateListingViewModel createListingViewModel;
    private CreateListingDataAccessInterface listingDataAccessInterface;
    private CreateListingPresenter createListingPresenter;
    private CreateListingInputBoundary createListingInteractor;
    private CreateListingController createListingController;
    BookInfoView bookInfoView;
    private JButton backButton;
    private JButton createListingButton;
    private JLabel titleLabel, isbnLabel, authorLabel, yearLabel, languageLabel, coverLabel;
    private JPanel subjectsPanel;
    private JPanel listingsPanel;

    public static Component getChildNamed(Component parent, String name){
        if (name.equals(parent.getName())){
            return parent;
        }
        if (parent instanceof Container) {
            Component[] children = (parent instanceof JButton) ? ((JButton)parent).getComponents():
                    ((Container)parent).getComponents();
            for (int i = 0; i < children.length; ++i){
                Component child = getChildNamed(children[i], name);
                if(child != null){
                    return child;
                }
            }
        }
        return null;
    }
    @BeforeEach
    void setUp() throws IOException {
        listingsFilePath = "listingsTest.csv";
        listingInfo = new File(listingsFilePath);
        listingFactory = new ListingFactory();
        bookViewModel = new BookInfoViewModel();
        model = new ViewManagerModel();
        createListingViewModel = new CreateListingViewModel();
        listingDataAccessInterface = new FileListingDataAccessObject(listingsFilePath, listingFactory);
        createListingPresenter = new CreateListingPresenter(model, createListingViewModel, bookViewModel);
        createListingInteractor = new CreateListingInteractor(listingDataAccessInterface,
                createListingPresenter, listingFactory);
        createListingController = new CreateListingController(createListingInteractor);
        bookInfoView = new BookInfoView(bookViewModel, model, createListingViewModel, createListingController);
    }
    @Test
    void backButtonUsedTest() {
        bookInfoView.setVisible(true);
        backButton = (JButton) getChildNamed(bookInfoView, "back");
        assertNotNull(backButton, "Cannot access back button");
        ActionEvent e = new ActionEvent(backButton, 123, "Go Back to Book Search");
        bookInfoView.actionPerformed(e);
        assertEquals(model.getActiveView(), "book search");
    }
    @Test
    void createListingButtonUsedTest() {
        bookInfoView.setVisible(true);
        createListingButton = (JButton) getChildNamed(bookInfoView, "create listing");
        assertNotNull(createListingButton, "Cannot access create listing button");
        ActionEvent e = new ActionEvent(createListingButton, 123, "Go to Create Listing menu");
        bookInfoView.actionPerformed(e);
        assertEquals(model.getActiveView(), "create listing");
    }
    @Test
    void bookInfoUpdateUITest() {
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Normandy");

        List<List<String>> listingInfo = new ArrayList<>();
        List<String> oneListing = new ArrayList<>();
        oneListing.add("History of Britain");
        oneListing.add("Dan Jones");
        oneListing.add(String.valueOf(2008));
        oneListing.add("9876543210000");
        oneListing.add("https://covers.openlibrary.org/b/id/12009823-L.jpg");
        oneListing.add("English");
        oneListing.add(String.valueOf(subjects));
        listingInfo.add(oneListing);

        BookInfoState newState = new BookInfoState();
        newState.setTitle("History of Britain");
        newState.setAuthor("Dan Jones");
        newState.setYear(2008);
        newState.setISBN("9876543210000");
        newState.setCoverURL("https://covers.openlibrary.org/b/id/12009823-L.jpg");
        newState.setLanguage("English");
        newState.setSubjects(subjects);
        newState.setListingsInfo(listingInfo);

        bookViewModel.updateBookInfo("History of Britain", 2008, "Dan Jones", "9876543210000",
                "https://covers.openlibrary.org/b/id/12009823-L.jpg", "English", subjects, listingInfo);

        assertEquals(newState, bookViewModel.getState());

    }
}