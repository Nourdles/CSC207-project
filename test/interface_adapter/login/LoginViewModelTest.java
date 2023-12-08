package interface_adapter.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewModelTest {
    private LoginViewModel model = new LoginViewModel();

    @Test
    void setState() {
        LoginState state = new LoginState();
        model.setState(state);
        assertEquals(state, model.getState());
    }

    @Test
    void getState() {
        LoginState state = new LoginState();
        model.setState(state);
        assertEquals(state, model.getState());
    }
}