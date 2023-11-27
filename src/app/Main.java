package app;
import use_case.booksearch.*;
import interface_adapter.booksearch.*;
import view.*;
import data_access.*;

public class Main {
    public static void main(String[] args) {
        // Real implementation of BookSearchDataAccessInterface
        BookSearchDataAccessInterface realDataAccess = new OpenLibraryDB();

        // Initialize the presenter and view model
        BookSearchViewModel viewModel = new BookSearchViewModel();
        BookSearchPresenter presenter = new BookSearchPresenter(viewModel);

        // Initialize the interactor (use case) with the real data access and presenter
        BookSearchInteractor interactor = new BookSearchInteractor(realDataAccess, presenter);

        // Initialize the controller with the interactor
        BookSearchController controller = new BookSearchController(interactor);

        // Initialize and display the BookSearchView
        BookSearchView view = new BookSearchView(controller, viewModel);
        view.display();
    }
}
