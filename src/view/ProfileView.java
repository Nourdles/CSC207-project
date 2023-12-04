package view;

import interface_adapter.Listings.ListingsController;
import interface_adapter.Listings.ListingsState;
import interface_adapter.Listings.ListingsViewModel;
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
    private JButton editCity;
    private JButton viewListing;


    public ProfileView(ProfileController controller, ProfileViewModel viewModel, ListingsViewModel listingsViewModel,ListingsController listingsController,
                       ViewManagerModel viewManagerModel) {
        this.profileController = controller;
        this.profileViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.listingsViewModel = listingsViewModel;
        this.listingsController = listingsController;
        viewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        viewListing = new JButton("View Listings");
        buttons.add(viewListing);
        Color Brown = new Color(217, 196, 152);
        viewListing.setBackground(Brown);

        viewListing.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(viewListing)){
                            ListingsState state = listingsViewModel.getState();
                            listingsController.execute(state.getUsername());
                        }
                    }
                }
        );

    }

    private void createUI() {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
