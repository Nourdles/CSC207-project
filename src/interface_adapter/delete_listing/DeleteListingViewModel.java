package interface_adapter.delete_listing;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteListingViewModel extends ViewModel {
    private DeleteListingState deleteListingState = new DeleteListingState();

    /**
     * Create a new Delete Listings View Model with the view name "delete listing"
     */
    public DeleteListingViewModel(){super("delete listing");}

    /**
     * Set the Delete Listing State of the View Model to the given DeleteListingState
     * @param state DeleteListingState we want to set the State of the View Model to
     */
    public void setState(DeleteListingState state){
        this.deleteListingState = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.deleteListingState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }

    /**
     * Returns the Delete Listing State of the current View Model
     * @return DeleteListingState representing the Delete Listing State of the current View Model
     */
    public DeleteListingState getState(){return deleteListingState;}
}
