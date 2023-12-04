package interface_adapter.create_listing;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A View Model for the Create Listing View.
 */
public class CreateListingViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Create Listing View";
    public static final String LISTING_PRICE_LABEL = "Listing Price";
    public static final String CONDITION_LABEL = "Condition";
    public static final String UPLOAD_BUTTON_LABEL = "Upload Image";
    public static final String CREATE_LISTING_LABEL = "Create Listing";
    private CreateListingState state;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
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

    public CreateListingState getState() { return state;}
    public void setState(CreateListingState state) {this.state = state;}
}
