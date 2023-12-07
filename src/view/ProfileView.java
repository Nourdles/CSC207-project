package view;

import interface_adapter.view_listings.ListingsController;
import interface_adapter.view_listings.ListingsState;
import interface_adapter.view_listings.ListingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "profile";
    private final ProfileViewModel profileViewModel;
    private final ProfileController profileController;
    private ViewManagerModel viewManagerModel;
    private final ListingsViewModel listingsViewModel;
    private final ListingsController listingsController;
    private JButton editPassword;

    private JButton viewListing;

    /**
     * Create a new Profile View with the given parameters
     * @param controller Profile Controller
     * @param viewModel Profile View Model
     * @param listingsViewModel Listings View Model
     * @param listingsController Listings Controller
     * @param viewManagerModel View Manager Model
     */
    public ProfileView(ProfileController controller, ProfileViewModel viewModel, ListingsViewModel listingsViewModel,ListingsController listingsController,
                       ViewManagerModel viewManagerModel) {
        this.profileController = controller;
        this.profileViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.listingsViewModel = listingsViewModel;
        this.listingsController = listingsController;
        viewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        viewListing = new JButton("View Listings");
        Color Brown = new Color(217, 196, 152);
        viewListing.setBackground(Brown);
        viewListing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(viewListing)) {
                    ListingsState state = listingsViewModel.getState();
                    listingsController.execute(state.getUsername());
                }
            }
        });

        JPanel centerPanel = new JPanel();
        centerPanel.add(viewListing);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
