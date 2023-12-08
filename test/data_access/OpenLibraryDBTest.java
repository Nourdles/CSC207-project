package data_access;

import entity.Book;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OpenLibraryDBTest {
    private final OkHttpClient client = new OkHttpClient();
    private OpenLibraryDB openLibraryDB = new OpenLibraryDB();

    @Test
    void getSearchResult() {
        ArrayList<Book> expectedResults = new ArrayList<>();
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("World history");
        subjects.add("Middle ages");
        subjects.add("Medieval Civilization");
        expectedResults.add(new Book("Powers and Thrones", 2021, "Dan Jones", "",
                "198488087X", 0, "https://covers.openlibrary.org/b/id/11103696-L.jpg",
                "eng", subjects));
        ArrayList<Book> results = openLibraryDB.getSearchResult("9781789543537");
        assertTrue(expectedResults.size() == results.size());
        assertEquals(expectedResults.get(0).getYear(), results.get(0).getYear());
        assertEquals(expectedResults.get(0).getAuthor(), results.get(0).getAuthor());
        assertEquals(expectedResults.get(0).getInStock(), results.get(0).getInStock());
        assertEquals(expectedResults.get(0).getCoverUrl(), results.get(0).getCoverUrl());
        assertEquals(expectedResults.get(0).getTitle(), results.get(0).getTitle());
        assertEquals(expectedResults.get(0).getSummary(), results.get(0).getSummary());
        assertEquals(expectedResults.get(0).getISBN(), results.get(0).getISBN());
    }
}