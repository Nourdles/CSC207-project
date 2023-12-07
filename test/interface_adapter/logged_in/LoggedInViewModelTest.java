package interface_adapter.logged_in;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggedInViewModelTest {
    private LoggedInViewModel model = new LoggedInViewModel();

    @Test
    void setState() {
        LoggedInState state = new LoggedInState();
        model.setState(state);
        assertEquals(state, model.getState());
    }

    @Test
    void getState() {
        LoggedInState state = new LoggedInState();
        model.setState(state);
        assertEquals(state, model.getState());
    }
}