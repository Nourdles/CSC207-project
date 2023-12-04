package interface_adapter.Listings;

public class ListingsViewModel {

    public ListingsViewModel(){

    }
    private ListingsState state = new ListingsState();
    public ListingsState getState() {
        return state;
    }

    public void setState(ListingsState listingsState) {
    }
}
