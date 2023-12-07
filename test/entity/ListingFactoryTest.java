package entity;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ListingFactoryTest {

    @Test
    void create() {
        File file = new File("demo.txt");
        ListingFactory listingFactory = new ListingFactory();
        assertNotNull(listingFactory.create("123", "Dracula", "user", 50.5, "Excellent", file,
                LocalDateTime.MAX));
    }
}