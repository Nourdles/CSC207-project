package use_case.search_filter;

import entity.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchFilterInteractorTest {

    @Test
    void successTest() {
        String author = "J. K. Rowling";
        String year = "";
        String hasListings = "No";

        ArrayList<Book> initialBooks = new ArrayList<>();
        Book harryPotter1 = new Book("Philosopher's Stone", 1989, "J. K. Rowling", "summary",
                "123", 0, "URL", "english", new ArrayList<>());
        initialBooks.add(harryPotter1);
        Book harryPotter2 = new Book("Chamber of Secretes", 1991, "J. K. Rowling", "summary",
                "234", 1, "URL", "english", new ArrayList<>());
        initialBooks.add(harryPotter2);
        Book dracula = new Book("Dracula", 1867, "Bram Stoker", "summary",
                "345", 1, "URL", "english", new ArrayList<>());
        initialBooks.add(dracula);

        SearchFilterInputData searchFilterInputData = new SearchFilterInputData(author, year, hasListings, initialBooks);

        SearchFilterOutputBoundary searchFilterOutputBoundary = new SearchFilterOutputBoundary() {
            @Override
            public void presentSearchFilterResponse(SearchFilterOutputData outputData) {
                assertTrue(outputData.isSuccess());
                ArrayList<Book> filteredBooks = new ArrayList<>();
                filteredBooks.add(harryPotter1);
                assertEquals(outputData.getBooks(), filteredBooks);
            }
        };
        SearchFilterInputBoundary searchFilterInputBoundary = new SearchFilterInteractor(searchFilterOutputBoundary);
        searchFilterInputBoundary.applyFilters(searchFilterInputData);
    }
}