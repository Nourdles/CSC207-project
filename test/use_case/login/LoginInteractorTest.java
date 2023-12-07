package use_case.login;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class LoginInteractorTest {

    @Test
    void successTest() {
        CommonUser user = (CommonUser) new CommonUserFactory().create("Un", "Password123", LocalDateTime.now(),
                "unumail", "69", "to");
        LoginInputData inputData = new LoginInputData("Un", "Password123");
        LoginUserDataAccessInterface userRepo = new InMemoryUserDataAccessObject();
        userRepo.save(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertTrue(userRepo.existsByName("Un"));
                assertEquals("Password123", inputData.getPassword());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        LoginInputBoundary loginInputBoundary = new LoginInteractor(userRepo, successPresenter);
        loginInputBoundary.execute(inputData);
    }
    @Test
    void  UsernameFailTest(){

        CommonUser user = (CommonUser) new CommonUserFactory().create("Un", "Password123", LocalDateTime.now(),
                "unumail", "69", "to");
        LoginInputData loginInputData = new LoginInputData("Unu", "Password123");
        LoginUserDataAccessInterface userRepo = new InMemoryUserDataAccessObject();
        userRepo.save(user);

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
        CommonUser user = (CommonUser) new CommonUserFactory().create("Unu", "Password123", LocalDateTime.now(),
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
