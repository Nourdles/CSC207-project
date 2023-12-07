package use_case.book_search;

import entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search_filter.SearchFilterOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookSearchOutputDataTest {
    private ArrayList<Book> initialBooks;
    private BookSearchOutputData outputData;

    @BeforeEach
    void init() {
        initialBooks = new ArrayList<>();
        Book harryPotter1 = new Book("Philosopher's Stone", 1989, "J. K. Rowling", "summary",
                "123", 0, "URL", "english", new ArrayList<>());
        initialBooks.add(harryPotter1);
        outputData = new BookSearchOutputData(initialBooks);
    }

    @Test
    void isSuccessTrue() {
        assertTrue(outputData.isSuccess());
    }

    @Test
    void getBooksSuccess() {
        assertEquals(initialBooks, outputData.getBooks());
    }

    @Test
    void getErrorMessageFailure() {
        assertThrows(IllegalStateException.class, () -> outputData.getErrorMessage());
    }

    @Test
    void isSuccessFalse() {
        BookSearchOutputData outputData1 = new BookSearchOutputData("error message");
        assertFalse(outputData1.isSuccess());
    }

    @Test
    void getBooksFailure() {
        BookSearchOutputData outputData1 = new BookSearchOutputData("error message");
        assertThrows(IllegalStateException.class, () -> outputData1.getBooks());
    }

    @Test
    void getErrorMessageSuccess() {
        BookSearchOutputData outputData1 = new BookSearchOutputData("error message");
        assertEquals("error message", outputData1.getErrorMessage());
    }
}