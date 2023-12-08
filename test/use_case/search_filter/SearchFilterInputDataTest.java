package use_case.search_filter;

import entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchFilterInputDataTest {
    private SearchFilterInputData searchFilterInputData;

    @BeforeEach
    void init() {
        searchFilterInputData = new SearchFilterInputData("Bram Stoker", "1898", "Yes",
                new ArrayList<>());
    }

    @Test
    void getAuthor() {
        assertEquals("Bram Stoker",searchFilterInputData.getAuthor() );
    }

    @Test
    void getYear() {
        assertEquals("1898", searchFilterInputData.getYear());
    }

    @Test
    void getListings() {
        assertEquals("Yes", searchFilterInputData.getListings());
    }

    @Test
    void getInitialBooks() {
        assertEquals(new ArrayList<>(), searchFilterInputData.getInitialBooks());
    }
}