package use_case.signup;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.signup.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SignupInteractorTest {
    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Paul", "Password123", "Password123",
                "mail@mail.com", "4379950765", "Toronto");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("Paul", user.getUsername());
                assertNotNull(user.getCreationTime());
                assertTrue(userRepository.existsByName("Paul"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("Paul", "Password123", "Wrong123",
                "mail@mail.com", "4379950765", "Toronto");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("Paul", "Password123", "Wrong123",
                "mail@mail.com", "4379950765", "Toronto");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "Password123",  LocalDateTime.now(), "mail@mail.com", "4379950765", "Toronto");
        userRepository.save(user);

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordNotMetRequirement(){
        SignupInputData inputData = new SignupInputData("Paul", "password", "password",
                "mail@mail.com", "4379950765", "Toronto");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password does not meet requirement", error);
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);

    }

    @Test
    void failureEmailNotValid(){
        SignupInputData inputData = new SignupInputData("Paul", "Password123", "Password123",
                "mail.com", "4379950765", "Toronto");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case is success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Email is not valid. Please try another email", error);

            }
        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

}
