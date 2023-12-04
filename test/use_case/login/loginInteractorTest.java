package use_case.login;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.login.*;
import use_case.signup.SignupInputData;
import use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class loginInteractorTest {
    @Test
    void  UsernameFailTest(){

        User user = new CommonUserFactory().create("Unu", "Password123", LocalDateTime.now(),
                "unu@mail.com", "123455478", "Toronto");
        LoginInputData loginInputData = new LoginInputData("Unu", "Password123");
        LoginUserDataAccessInterface userRepo = new InMemoryUserDataAccessObject();
        //userRepo.save(user);

        LoginOutputBoundary failPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Unu: Account does not exist.", error);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userRepo, failPresenter);
        interactor.execute(loginInputData);
    }
    @Test
    void PasswordFailTest(){
        User user = new CommonUserFactory().create("Unu", "Password123", LocalDateTime.now(),
                "unu@mail.com", "123455478", "Toronto");
        LoginInputData loginInputData = new LoginInputData("Unu", "Password12");
        LoginUserDataAccessInterface userRepo = new InMemoryUserDataAccessObject();
        userRepo.save(user);

        LoginOutputBoundary failPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for Unu.", error);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userRepo, failPresenter);
        interactor.execute(loginInputData);
    }


}
