package interface_adapter.profile;

import interface_adapter.ViewModel;
import view.ProfileView;

import java.beans.PropertyChangeListener;

public class ProfileViewModel extends ViewModel {
    public void addPropertyChangeListener(ProfileView profileView) {
    }
    private ProfileState state = new ProfileState();

    public ProfileViewModel(){super("Profile");}

    public ProfileState getState() { return state;}

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
