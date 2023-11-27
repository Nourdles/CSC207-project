package interface_adapter.create_listing;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

/**
 * A View Model for the Create Listing View.
 */
public class CreateListingViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Create Listing View";
    public static final String LISTING_PRICE_LABEL = "Listing Price";
    public static final String CONDITION_LABEL = "Condition";
    public static final String UPLOAD_BUTTON_LABEL = "Upload Image";
    public static final String CREATE_LISTING_LABEL = "Create Listing";
    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
