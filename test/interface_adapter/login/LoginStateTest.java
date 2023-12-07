package interface_adapter.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginStateTest {
    private LoginState state = new LoginState();

    @Test
    void getUsername() {
        assertEquals("", state.getUsername());
    }

    @Test
    void getUsernameError() {
        assertNull(state.getUsernameError());
    }

    @Test
    void getPassword() {
        assertEquals("", state.getPassword());
    }

    @Test
    void getPasswordError() {
        assertNull(state.getPasswordError());
    }

    @Test
    void setUsername() {
        state.setUsername("user");
        assertEquals("user", state.getUsername());
    }

    @Test
    void setUsernameError() {
        state.setUsernameError("error");
        assertEquals("error", state.getUsernameError());
    }

    @Test
    void setPassword() {
        state.setPassword("password");
        assertEquals("password", state.getPassword());
    }
}