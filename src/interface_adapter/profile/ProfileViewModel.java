package interface_adapter.profile;

import interface_adapter.ViewModel;
import view.ProfileView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    public void addPropertyChangeListener(ProfileView profileView) {
    }
    private ProfileState state = new ProfileState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ProfileViewModel(){super("profile");}

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
