package use_case.search_filter;

import entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchFilterOutputDataTest {
    private SearchFilterOutputData searchFilterOutputData;
    private ArrayList<Book> initialBooks;

    @BeforeEach
    void init() {
        initialBooks = new ArrayList<>();
        Book harryPotter1 = new Book("Philosopher's Stone", 1989, "J. K. Rowling", "summary",
                "123", 0, "URL", "english", new ArrayList<>());
        initialBooks.add(harryPotter1);
        searchFilterOutputData = new SearchFilterOutputData(initialBooks);
    }

    @Test
    void isSuccessTrue() {
        assertTrue(searchFilterOutputData.isSuccess());
    }

    @Test
    void getBooksSuccess() {
        assertEquals(initialBooks, searchFilterOutputData.getBooks());
    }

    @Test
    void getErrorMessageFailure() {
        assertThrows(IllegalStateException.class, () -> searchFilterOutputData.getErrorMessage());
    }

    @Test
    void isSuccessFalse() {
        SearchFilterOutputData searchFilterOutput = new SearchFilterOutputData("error message");
        assertFalse(searchFilterOutput.isSuccess());
    }

    @Test
    void getBooksFailure() {
        SearchFilterOutputData searchFilterOutput = new SearchFilterOutputData("error message");
        assertThrows(IllegalStateException.class, () -> searchFilterOutput.getBooks());
    }

    @Test
    void getErrorMessageSuccess() {
        SearchFilterOutputData searchFilterOutput = new SearchFilterOutputData("error message");
        assertEquals("error message", searchFilterOutput.getErrorMessage());
    }
}