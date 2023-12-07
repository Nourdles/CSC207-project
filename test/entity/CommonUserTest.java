package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {

    private CommonUser user;

    @BeforeEach
    void init() {
        user = new CommonUser("username", "123**User", LocalDateTime.MAX, "user@mail.com",
                "1234567890", "Toronto");
    }

    @Test
    void getUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("123**User", user.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("user@mail.com", user.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("1234567890", user.getPhoneNumber());
    }

    @Test
    void getCity() {
        assertEquals("Toronto", user.getCity());
    }

    @Test
    void getCreationTime() {
        assertEquals(LocalDateTime.MAX, user.getCreationTime());
    }

    @Test
    void setUsername() {
        user.setUsername("nour");
        assertEquals("nour", user.getUsername());
    }

    @Test
    void setPassword() {
        user.setPassword("1234**User");
        assertEquals("1234**User", user.getPassword());
    }

    @Test
    void setEmail() {
        user.setEmail("user@yahoo.com");
        assertEquals("user@yahoo.com", user.getEmail());
    }

    @Test
    void setPhoneNumber() {
        user.setPhoneNumber("0987654321");
        assertEquals("0987654321", user.getPhoneNumber());
    }

    @Test
    void setCity() {
        user.setCity("Montreal");
        assertEquals("Montreal", user.getCity());
    }
}