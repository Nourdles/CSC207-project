package interface_adapter.create_listing;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateListingViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Create Listing View";
    public static final String UPLOAD_BUTTON_LABEL = "Upload Image";
    public static final String CREATE_LISTING_LABEL = "Create Listing";
    private CreateListingState state;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Create a new View Model and set its view name to "Create Listing"
     */
    public CreateListingViewModel(){
        super("Create Listing");
        this.state = new CreateListingState();
    }
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the Create Listing State of the View Model
     * @return The Create Listing State of the View Model
     */
    public CreateListingState getState() { return state;}

    /**
     * Set the Create Listing State of the View Model
     * @param state CreateListingState we want to set the Create Listing State of the View Model to
     */
    public void setState(CreateListingState state) {this.state = state;}
}
