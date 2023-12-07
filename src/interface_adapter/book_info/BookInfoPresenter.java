package interface_adapter.book_info;

import interface_adapter.ViewManagerModel;
import use_case.book_info.BookInfoOutputBoundary;
import use_case.book_info.BookInfoOutputData;

public class BookInfoPresenter implements BookInfoOutputBoundary {
    private final BookInfoViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Creates a Presenter for the Book Info use case
     * @param bookInfoViewModel view model for the book info use case
     * @param viewManagerModel manager for our different view models
     */
    public BookInfoPresenter(BookInfoViewModel bookInfoViewModel,
                             ViewManagerModel viewManagerModel) {
        this.viewModel = bookInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Calls the updateBookInfo method on the View Model if the isSuccess method for Output Data returns true, and
     * changes the view to the Book Info View, and displays an error message otherwise
     * @param outputData Output Data for the Book Info use case
     */
    @Override
    public void displayBookInfo(BookInfoOutputData outputData) {
        if (outputData.isSuccess()) {
            viewModel.updateBookInfo(
                    outputData.getTitle(),
                    outputData.getYear(),
                    outputData.getAuthor(),
                    outputData.getISBN(),
                    outputData.getCoverURL(),
                    outputData.getLanguage(),
                    outputData.getSubjects(),
                    outputData.getListingsDetails()
            );

            this.viewManagerModel.setActiveView(viewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else {
            viewModel.updateErrorMessage(outputData.getErrorMessage());
        }
    }
}
