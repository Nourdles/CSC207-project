package interface_adapter.logged_in;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggedInStateTest {
    private LoggedInState state = new LoggedInState();

    @Test
    void getUsername() {
        assertEquals("", state.getUsername());
    }

    @Test
    void setUsername() {
        state.setUsername("user");
        assertEquals("user", state.getUsername());
    }
}