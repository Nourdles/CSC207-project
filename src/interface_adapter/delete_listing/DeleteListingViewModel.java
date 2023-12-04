package interface_adapter.delete_listing;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteListingViewModel extends ViewModel {
    private DeleteListingState deleteListingState = new DeleteListingState();
    public DeleteListingViewModel(){super("delete listing");}
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
    public DeleteListingState getState(){return deleteListingState;}
}
