package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "profile";
    private final ProfileViewModel profileViewModel;
    private final ProfileController profileController;
    private ViewManagerModel viewManagerModel;


    public ProfileView(ProfileController controller, ProfileViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.profileController = controller;
        this.profileViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);
        createUI();
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
