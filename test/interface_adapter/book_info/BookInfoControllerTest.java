package interface_adapter.book_info;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.book_info.BookInfoInputBoundary;
import use_case.book_info.BookInfoInputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookInfoControllerTest {
    private BookInfoInputBoundary inputBoundaryMock;
    private BookInfoController controller;

    @Test
    void onBookSelected() {
        inputBoundaryMock = Mockito.mock(BookInfoInputBoundary.class);
        controller = new BookInfoController(inputBoundaryMock);
        controller.onBookSelected("title", 2000, "author", "ISBN", "URL", "en",
                new ArrayList<>());
        Mockito.verify(inputBoundaryMock).showBookDetails(Mockito.any(BookInfoInputData.class));
    }
}