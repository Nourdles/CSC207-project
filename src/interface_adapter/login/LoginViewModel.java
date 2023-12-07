package interface_adapter.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {

    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String SIGN_UP_BUTTON_LABEL = "Don't have an account? Sign up";
    private LoginState state = new LoginState();

    /**
     * Creates a new Login View Model with the view name "log in"
     */
    public LoginViewModel() {
        super("log in");
    }

    /**
     * Set the Login State of the View Model to the given LoginState
     * @param state LoginState we want to set the State of the View Model to
     */
    public void setState(LoginState state) {
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
     * Returns the Login State of the View Model
     * @return LoginState that is the State of the View Model
     */
    public LoginState getState() {
        return state;
    }
}
