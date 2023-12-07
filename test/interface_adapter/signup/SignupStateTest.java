package interface_adapter.signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupStateTest {
    private final SignupState signupState = new SignupState();

    @Test
    void getUsername() {
        assertEquals("", signupState.getUsername());

    }

    @Test
    void getUsernameError() {
        assertNull(signupState.getUsernameError());
    }

    @Test
    void getPassword() {
        assertEquals("", signupState.getPassword());
    }

    @Test
    void getRepeatPassword() {
        assertEquals("", signupState.getRepeatPassword());
    }

    @Test
    void getEmail() {
        assertEquals("", signupState.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertNull(signupState.getPhoneNumber());
    }

    @Test
    void getCity() {
        assertEquals("", signupState.getCity());
    }

    @Test
    void setUsername() {
        signupState.setUsername("user");
        assertEquals("user", signupState.getUsername());
    }

    @Test
    void setUsernameError() {
        signupState.setUsernameError("error");
        assertEquals("error", signupState.getUsernameError());
    }

    @Test
    void setPassword() {
        signupState.setPassword("password");
        assertEquals("password", signupState.getPassword());
    }

    @Test
    void setRepeatPassword() {
        signupState.setRepeatPassword("password");
        assertEquals("password", signupState.getRepeatPassword());
    }

    @Test
    void setEmail() {
        signupState.setEmail("user@mail.com");
        assertEquals("user@mail.com", signupState.getEmail());
    }

    @Test
    void setPhoneNumber() {
        signupState.setPhoneNumber("123");
        assertEquals("123", signupState.getPhoneNumber());
    }

    @Test
    void setCity() {
        signupState.setCity("Toronto");
        assertEquals("Toronto", signupState.getCity());
    }

    @Test
    void testToString() {
        assertEquals("SignupState{" +
                "username='" + "" + '\'' +
                ", password='" + "" + '\'' +
                ", repeatPassword='" + "" + '\'' +
                ", email='" + "" + '\'' +
                ", phoneNumber='" + null + '\'' +
                ", city='" + "" + '\'' +
                '}', "SignupState{" +
                "username='" + signupState.getUsername() + '\'' +
                ", password='" + signupState.getPassword() + '\'' +
                ", repeatPassword='" + signupState.getRepeatPassword() + '\'' +
                ", email='" + signupState.getEmail() + '\'' +
                ", phoneNumber='" + signupState.getPhoneNumber() + '\'' +
                ", city='" + signupState.getCity() + '\'' +
                '}');
    }
}