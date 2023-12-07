package use_case.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SignupOutputDataTest {
    private SignupOutputData signupOutputData;

    @BeforeEach
    void init(){
        signupOutputData = new SignupOutputData("username", "2023-11-29T22:54:31.642144",
                false);
    }

    @Test
    void getUsername() {
        assertEquals("username", signupOutputData.getUsername());
    }

    @Test
    void getCreationTime() {
        assertEquals("2023-11-29T22:54:31.642144", signupOutputData.getCreationTime());
    }

    @Test
    void setCreationTime() {
        signupOutputData.setCreationTime("2023-11-29T22:53:04.255264");
        assertEquals("2023-11-29T22:53:04.255264", signupOutputData.getCreationTime());
    }
}