package interface_adapter.signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupViewModelTest {
    private SignupViewModel model = new SignupViewModel();

    @Test
    void setState() {
        SignupState state = new SignupState();
        model.setState(state);
        assertEquals(state, model.getState());
    }

    @Test
    void getState() {
        SignupState state = new SignupState();
        model.setState(state);
        assertEquals(state, model.getState());
    }
}