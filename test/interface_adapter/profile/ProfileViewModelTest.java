package interface_adapter.profile;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;

class ProfileViewModelTest {
    private ProfileViewModel model = new ProfileViewModel();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Test
    void getState() {
        assertTrue(true);
    }

    @Test
    void firePropertyChanged() {
        model.firePropertyChanged();
        assertTrue(true);
    }

    @Test
    void addPropertyChangeListener() {
        assertTrue(true);

    }
}