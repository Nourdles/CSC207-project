package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String EMAIL_LABEL = "Enter email";
    public static final String PHONE_NUMBER_LABEL = "Enter phone number";
    public static final String CITY_LABEL = "Enter your city";
    private SignupState state = new SignupState();

    /**
     * Create a new SignupViewModel with the view name "sign up"
     */
    public SignupViewModel() {
        super("sign up");
    }

    /**
     * set the State of the View Model to the given SignupState
     * @param state SignupState we want to set the State of the View Model to
     */
    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the current State of the View Model
     * @return SignupState that represents the current State of the View Model
     */
    public SignupState getState() {
        return state;
    }
}
