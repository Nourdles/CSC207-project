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

    private JButton viewListing;

    public ProfileView(ProfileController controller, ProfileViewModel viewModel, ListingsViewModel listingsViewModel,ListingsController listingsController,
                       ViewManagerModel viewManagerModel) {
        this.profileController = controller;
        this.profileViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.listingsViewModel = listingsViewModel;
        this.listingsController = listingsController;
        //this.viewManagerModel.addPropertyChangeListener(this);
        viewModel.addPropertyChangeListener(this);
        listingsViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();


        viewListing = new JButton("View listings");
        Color Brown = new Color(217, 196, 152);
        viewListing.setBackground(Brown);
        centerPanel.add(viewListing);

        viewListing.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(viewListing)) {
//                    ListingsState state = listingsViewModel.getState();
//                    listingsController.execute(state.getUsername());
                    viewManagerModel.setActiveView(listingsViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });



        this.add(centerPanel, BorderLayout.CENTER);

    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewListing)){
        viewManagerModel.setActiveView(listingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();}

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {


    }
}
