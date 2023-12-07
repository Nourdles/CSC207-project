package interface_adapter.logged_in;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {

    private LoggedInState state = new LoggedInState();
    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    private String loggedInUser;

    /**
     * Creates a new Logged In View Model with the view name "logged in"
     */
    public LoggedInViewModel() {
        super("logged in");
    }

    /**
     * Set the State of the View Model to the given LoggedInState
     * @param state LoggedInState we want to set to be the State of the current View Model
     */
    public void setState(LoggedInState state) {
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
     * Returns the State of the View Model
     * @return LoggedInState that is the State of the View Model
     */
    public LoggedInState getState() {
        return state;
    }
}
