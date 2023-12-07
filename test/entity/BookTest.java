package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    void init() {
        book = new Book("Dracula", 1988, "Bram Stoker", "Summary", "123", 0,
                "URL", "english", new ArrayList<>());
    }

    @Test
    void getTitle() {
        assertEquals("Dracula", book.getTitle());
    }

    @Test
    void setTitle() {
        book.setTitle("The Bible");
        assertEquals("The Bible", book.getTitle());
    }

    @Test
    void getYear() {
        assertEquals(1988, book.getYear());
    }

    @Test
    void setYear() {
        book.setYear(1217);
        assertEquals(1217, book.getYear());
    }

    @Test
    void getAuthor() {
        assertEquals("Bram Stoker", book.getAuthor());
    }

    @Test
    void setAuthor() {
        book.setAuthor("King James");
        assertEquals("King James", book.getAuthor());
    }

    @Test
    void getSummary() {
        assertEquals("Summary", book.getSummary());
    }

    @Test
    void getISBN() {
        assertEquals("123", book.getISBN());
    }

    @Test
    void setISBN() {
        book.setISBN("456");
        assertEquals("456", book.getISBN());
    }

    @Test
    void getInStock() {
        assertEquals(0, book.getInStock());
    }

    @Test
    void testToString() {
        assertEquals(book.toString()

        , "Book{" +
                "title='" + book.getTitle() + '\'' +
                ", year=" + book.getYear() +
                ", author='" + book.getAuthor() + '\'' +
                ", summary='" + book.getSummary() + '\'' +
                ", ISBN=" + book.getISBN() +
                ", inStock=" + book.getInStock() +
                ", coverUrl='" + book.getCoverUrl() + '\'' +
                '}');
    }

    @Test
    void getCoverUrl() {
        assertEquals("URL", book.getCoverUrl());
    }

    @Test
    void getLanguage() {
        assertEquals("english", book.getLanguage());
    }

    @Test
    void getSubjects() {
        ArrayList<String> genres = new ArrayList<>();
        assertEquals(genres, book.getSubjects());
    }
}