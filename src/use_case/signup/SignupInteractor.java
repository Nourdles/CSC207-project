package use_case.signup;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    /**
     * Creates a new Signup Interactor with the given parameters
     * @param signupDataAccessInterface Signup Data Access Interface
     * @param signupOutputBoundary Signup Output Boundary
     * @param userFactory User Factory
     */
    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Executing the use case based on the Signup Data: call the prepareFailView of the Presenter the User already
     * exists, the repeat password does not match the password, or either the email or password do not meet the
     * security requirements. Otherwise, call the prepareSuccessView method.
     * @param signupInputData Signup Input Data
     */
    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else if (!signupInputData.passwordMeetsReq(signupInputData.getPassword())) {
            userPresenter.prepareFailView("Password does not meet requirement");

        } else if (signupInputData.emailValid(signupInputData.getEmail())) {
            userPresenter.prepareFailView("Email is not valid. Please try another email");
        } else {
            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(),now, signupInputData.getEmail(),
                signupInputData.getPhoneNumber(), signupInputData.getCity());
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(),now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}