package use_case.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginOutputDataTest {

    @Test
    void getUsername() {
        LoginOutputData loginOutputData = new LoginOutputData("username", false);
        assertEquals("username", loginOutputData.getUsername());
    }
}