package interface_adapter.delete_listing;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteListingViewModel extends ViewModel {
    private DeleteListingState deleteListingState = new DeleteListingState();
    public DeleteListingViewModel(){super("delete listing");}
    public void setState(DeleteListingState state){
        this.deleteListingState = state;
    }
    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
    public DeleteListingState getState(){return deleteListingState;}
}
