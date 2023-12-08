package interface_adapter.login;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.login.*;


class LoginControllerTest {
    private LoginInputBoundary loginInputBoundaryMock;
    private LoginController loginController;

    @Test
    void execute() {
        loginInputBoundaryMock = Mockito.mock(LoginInputBoundary.class);
        loginController = new LoginController(loginInputBoundaryMock);
        String username = "user";
        String password = "password";
        loginController.execute(username, password);
        Mockito.verify(loginInputBoundaryMock).execute(Mockito.any(LoginInputData.class));
    }
}