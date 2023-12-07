package interface_adapter.view_listings;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ListingsViewModel  extends ViewModel {
    public final String TITLE_LABEL = "Listings view";
    private ListingsState state = new ListingsState();
    public static final String DELETE_BUTTON_LABEL = "Delete";
    public static final String BACK_BUTTON_LABEL = "Back";
    private String listings;

    /**
     * Create a new Listings View Model the view name "listings"
     */
    public ListingsViewModel() {
        super("listings");
    }

    /**
     * Sets the Listing State of the View Model to a give ListingState
     * @param state ListingState that represents the State of the View Model
     */
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

    /**
     * Returns the State of the View Model
     * @return ListingsState that represents the State of the View Model
     */
    public ListingsState getState(){
        return state;
    }

    /**
     * Returns the Listings of the currently logged-in User stored in the View Model
     * @return String that represents the Listings of the currently logged-in User stored in the View Model
     */
    public String getListings(){
        return listings;
    }

    /**
     * Sets the Listings of the currently logged-in User stored in the View Model
     * @param listings String that represents the Listings of the currently logged-in User stored in the View Model
     */
    public void setListings(String listings){
        this.listings = listings;
    }

}