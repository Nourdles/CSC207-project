package interface_adapter.profile;

import interface_adapter.ViewModel;
import view.ProfileView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    private ProfileState state = new ProfileState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Create a new Profile View Model with the view name "profile"
     */
    public ProfileViewModel(){super("profile");}

    /**
     * Returns the Profile State of the View Model
     * @return ProfileState that represents the State of the View Model
     */
    public ProfileState getState() { return state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
