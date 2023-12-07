package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AdminUserTest {

    private AdminUser admin;

    @BeforeEach
    void init() {
        admin = new AdminUser("username", "123**User", LocalDateTime.MAX, "user@mail.com",
                "1234567890", "Toronto");
    }

    @Test
    void setPassword() {
        admin.setPassword("124**User");
        assertEquals("124**User", admin.getPassword());
    }

    @Test
    void setUsername() {
        admin.setUsername("admin");
        assertEquals("admin", admin.getUsername());
    }

    @Test
    void setEmail() {
        admin.setEmail("admin@mail.com");
        assertEquals("admin@mail.com", admin.getEmail());
    }

    @Test
    void setPhoneNumber() {
        admin.setPhoneNumber("0987654321");
        assertEquals("0987654321", admin.getPhoneNumber());
    }

    @Test
    void setCity() {
        admin.setCity("Montreal");
        assertEquals("Montreal", admin.getCity());
    }

    @Test
    void getUsername() {
        assertEquals("username", admin.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("123**User", admin.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("user@mail.com", admin.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("1234567890", admin.getPhoneNumber());
    }

    @Test
    void getCity() {
        assertEquals("Toronto", admin.getCity());
    }

    @Test
    void getCreationTime() {
        assertEquals(LocalDateTime.MAX, admin.getCreationTime());
    }
}