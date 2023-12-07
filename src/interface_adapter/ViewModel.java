package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {
    private String viewName;

    /**
     * Create a new ViewModel with the given view name
     * @param viewName String we want to set the view name to
     */
    public ViewModel(String viewName) {

        this.viewName = viewName;
    }

    /**
     * Returns the view name of the View Model
     * @return
     */
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}
