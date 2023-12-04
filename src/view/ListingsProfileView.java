package view;

import interface_adapter.Listings.ListingsState;
import interface_adapter.Listings.ListingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_listing.DeleteListingController;
import interface_adapter.delete_listing.DeleteListingState;
import interface_adapter.delete_listing.DeleteListingViewModel;
import entity.Listing;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ListingsProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * A view of a seller's own listings.
     */
    public final String viewName = "Listings view";

    private final ListingsViewModel listingViewModel;
    private final DeleteListingController deleteListingController;
    private final ViewManagerModel viewManagerModel;
    private final  DeleteListingViewModel deleteListingViewModel;

    private final ProfileViewModel profileViewModel;
//    private final ProfileViewModel profileViewModel;

    JLabel listings;

    final JButton delete;
    final JButton back;

    public ListingsProfileView(ListingsViewModel listingsViewModel, DeleteListingController deleteListingController,
                               ViewManagerModel viewManagerModel, DeleteListingViewModel deleteListingViewModel,
                               ProfileViewModel profileViewModel){
        this.listingViewModel = listingsViewModel;
        this.listingViewModel.addPropertyChangeListener(this);
        this.deleteListingController = deleteListingController;
        this.viewManagerModel = viewManagerModel;
        this.deleteListingViewModel = deleteListingViewModel;
        this.profileViewModel = profileViewModel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JLabel title = new JLabel(listingsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel listingInfo = new JLabel("Your listings:");
        listings = new JLabel();

        JPanel panel= new JPanel();
        JComboBox<String> listingDropdown = new JComboBox<>(listingsViewModel.getState().getListings().toArray(new String[0]));
        panel.add(listingDropdown);
        delete = new JButton(ListingsViewModel.DELETE_BUTTON_LABEL);
        panel.add(delete);

        back = new JButton(ListingsViewModel.BACK_BUTTON_LABEL);
        panel.add(back);

        this.add(title);
        this.add(listingInfo);

        //Populate layout with listings
        for (Listing listing: listingsViewModel.getState().getListings()){
            ImageIcon icon = new ImageIcon(listing.getBookPhoto().getPath());
            Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);

            JLabel picturelabel = new JLabel(icon);
            picturelabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel bookname = new JLabel("Book name:" + listing.getTitle());
            bookname.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel listingId = new JLabel("Listing Id:" + listing.getListingId());
            listingId.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(picturelabel);
            this.add(bookname);
            this.add(listingId);
            this.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        JScrollPane scrollPane = new JScrollPane(this);
        this.add(scrollPane);
        this.add(panel);


        delete.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(delete)){
                    String selectedListing = (String)  listingDropdown.getSelectedItem();
                    DeleteListingState state = deleteListingViewModel.getState();
                    state.setListing(selectedListing);
                    deleteListingController.execute(state.getListing());

                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)){
                    viewManagerModel.setActiveView(profileViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

    }


    public void actionPerformed(ActionEvent e) {
        System.out.println("Takes the user back to profile view. Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ListingsState state = (ListingsState) evt.getNewValue();
        //listings.setText(state.getListingsString());
    }
}
