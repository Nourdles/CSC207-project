package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AdminUserFactoryTest {


    @Test
    void create() {
        AdminUserFactory userFactory = new AdminUserFactory();
        User user = userFactory.create("username", "123**User", LocalDateTime.MAX,
                "user@mail.com", "1234567890", "Toronto");

        assertTrue(user instanceof AdminUser);
    }
}