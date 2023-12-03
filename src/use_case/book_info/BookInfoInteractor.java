package use_case.book_info;

public class BookInfoInteractor implements BookInfoInputBoundary{
    final BookInfoOutputBoundary bookInfoOutputPresenter;

    public BookInfoInteractor(BookInfoOutputBoundary bookInfoOuputPresenter) {
        this.bookInfoOutputPresenter = bookInfoOuputPresenter;
    }

    @Override
    public void showBookDetails(BookInfoInputData inputData) {
        try {
            BookInfoOutputData outputData = new BookInfoOutputData(
                    inputData.getTitle(),
                    inputData.getYear(),
                    inputData.getAuthor(),
                    inputData.getISBN(),
                    inputData.getCoverURL(),
                    inputData.getLanguage(),
                    inputData.getSubjects(),
                    null
            );
            bookInfoOutputPresenter.displayBookInfo(outputData);

        } catch (Exception e) {
            BookInfoOutputData errorOutputData = new BookInfoOutputData(
                    null, 0, null, null, null, null, null, e.getMessage()
            );
            bookInfoOutputPresenter.displayBookInfo(errorOutputData);

        }

    }
}
