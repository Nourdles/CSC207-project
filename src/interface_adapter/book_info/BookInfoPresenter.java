package interface_adapter.book_info;

import interface_adapter.ViewManagerModel;
import use_case.book_info.BookInfoOutputBoundary;
import use_case.book_info.BookInfoOutputData;

public class BookInfoPresenter implements BookInfoOutputBoundary {
    private final BookInfoViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public BookInfoPresenter(BookInfoViewModel bookInfoViewModel,
                             ViewManagerModel viewManagerModel) {
        this.viewModel = bookInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }

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
