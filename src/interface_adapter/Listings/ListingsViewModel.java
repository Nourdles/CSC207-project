package interface_adapter.Listings;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ListingsViewModel  extends ViewModel {
    public final String TITLE_LABEL = "Listings view";
    private ListingsState state = new ListingsState();
    public static final String DELETE_BUTTON_LABEL = "Delete";
    public static final String BACK_BUTTON_LABEL = "Back";
    private String listings;

    public ListingsViewModel() {
        super("listings");
    }
    public void setState(ListingsState state){
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public ListingsState getState(){
        return state;
    }
    public String getListings(){
        return listings;
    }
    public void setListings(String listings){
        this.listings = listings;
    }

}