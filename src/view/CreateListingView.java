package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.book_info.BookInfoController;
import interface_adapter.book_info.BookInfoViewModel;
import interface_adapter.create_listing.CreateListingController;
import interface_adapter.create_listing.CreateListingState;
import interface_adapter.create_listing.CreateListingViewModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class CreateListingView extends JPanel implements ActionListener, PropertyChangeListener {
    /**
     * The View allowing sellers to input listing information to create a book.
     */
    public final String viewName = "create listing";
    final JButton upload;
    final JButton createListing;
    private JButton cancelButton;
    private final CreateListingViewModel createListingViewModel;
    private final CreateListingController createListingController;
    private final ViewManagerModel viewManagerModel;
    private final BookInfoController bookInfoController;
    private final BookInfoViewModel bookInfoViewModel;
    private final DecimalFormat listingPriceFormat = new DecimalFormat("##,###.00");
    private final JTextField listingPriceInputField = new JTextField(10);
    private final JFileChooser bookPhotoChooser = new JFileChooser();
    private final String[] options = new String[]{"New", "Excellent", "Good", "Ok", "Poor"};
    private final String selectedCondition = "New";
    private final JComboBox<String> conditionDropdown = new JComboBox<String>(options);
    private File image;

    /**
     * Create a new Create Listing View
     * @param createListingViewModel Create Listing View Model
     * @param createListingController Create Listing Controller
     * @param viewManagerModel View Manager Model
     * @param bookInfoController Book Info Controller
     * @param bookInfoViewModel Book Info View Model
     */
    public CreateListingView(CreateListingViewModel createListingViewModel,
                             CreateListingController createListingController, ViewManagerModel viewManagerModel, BookInfoController bookInfoController, BookInfoViewModel bookInfoViewModel){
        this.createListingViewModel = createListingViewModel;
        this.createListingController = createListingController;
        this.viewManagerModel = viewManagerModel;
        this.bookInfoController = bookInfoController;
        this.bookInfoViewModel = bookInfoViewModel;
        this.createListingViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        Color Brown = new Color(217, 196, 152);
        Color lightBrown = new Color(245, 229, 196);
        Color whiteBrown = new Color(224, 218, 213);

        JLabel title = new JLabel(CreateListingViewModel.TITLE_LABEL);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBackground(lightBrown);
        this.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(lightBrown);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JPanel priceInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        LabelTextPanel priceLabel = new LabelTextPanel(new JLabel("Price (CAD):"), listingPriceInputField);
        priceInputPanel.add(priceLabel);
        priceLabel.setBackground(lightBrown);
        priceInputPanel.setBackground(lightBrown);
        listingPriceInputField.setBackground(whiteBrown);

        priceInputPanel.add(listingPriceInputField);
        centerPanel.add(priceInputPanel);

        JPanel uploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        uploadPanel.setBackground(lightBrown);
        upload = new JButton(CreateListingViewModel.UPLOAD_BUTTON_LABEL);
        upload.setBackground(Brown);
        uploadPanel.add(upload);
        centerPanel.add(uploadPanel);

        JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        conditionPanel.setBackground(lightBrown);
        JLabel conditionLabel = new JLabel("Condition:");
        conditionPanel.add(conditionLabel);
        conditionPanel.add(conditionDropdown);
        conditionDropdown.setBackground(Brown);
        centerPanel.add(conditionPanel);

        this.add(centerPanel, BorderLayout.CENTER);

        createListing = new JButton(CreateListingViewModel.CREATE_LISTING_LABEL);
        cancelButton = new JButton("Cancel");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(lightBrown);
        cancelButton.setBackground(Brown);
        createListing.setBackground(Brown);
        southPanel.add(createListing);
        southPanel.add(cancelButton);
        this.add(southPanel, BorderLayout.SOUTH);

        conditionDropdown.addActionListener(this);
        createListing.addActionListener(this);
        cancelButton.addActionListener(this);

        upload.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(upload)) {
                        bookPhotoChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                        bookPhotoChooser.setFileFilter(filter);
                        int returnVal = bookPhotoChooser.showOpenDialog(CreateListingView.this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            image = bookPhotoChooser.getSelectedFile();
                            CreateListingState currentState = createListingViewModel.getState();
                            currentState.setBookPhoto(image);
                            createListingViewModel.setState(currentState);
                        }
                    }
                }
            }
        );

        listingPriceInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    double price = Double.parseDouble(listingPriceInputField.getText());
                    CreateListingState currentState = createListingViewModel.getState();
                    currentState.setListingPrice(price);
                    createListingViewModel.setState(currentState);
                } catch (NumberFormatException ex) {
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(createListing)) {
            LocalDateTime ltd = LocalDateTime.now();
            CreateListingState currentState = createListingViewModel.getState();
            try {
                createListingController.execute(currentState.getTitle(), currentState.getBookISBN(), currentState.getSeller(),
                        currentState.getListingPrice(), currentState.getCondition(),
                        currentState.getBookPhoto(), ltd);

                bookInfoController.onBookSelected(bookInfoViewModel.getState().getTitle(),
                        bookInfoViewModel.getState().getYear(),
                        bookInfoViewModel.getState().getAuthor(),
                        bookInfoViewModel.getState().getISBN(),
                        bookInfoViewModel.getState().getCoverURL(),
                        bookInfoViewModel.getState().getLanguage(),
                        bookInfoViewModel.getState().getSubjects()

                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (evt.getSource().equals(conditionDropdown)) {
            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
            String selectedCondition = (String) cb.getSelectedItem();
            CreateListingState currentState = createListingViewModel.getState();
            currentState.setCondition(selectedCondition);
            createListingViewModel.setState(currentState);
        } else if (evt.getSource().equals(cancelButton)){
            CreateListingView.this.viewManagerModel.setActiveView("book info");
            CreateListingView.this.viewManagerModel.firePropertyChanged();
        }
    }
        @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateListingState state = (CreateListingState) evt.getNewValue();
        setFields(state);
    }

    /**
     * Set a format for the price input field
     * @param state the current Create Listing State
     */
    private void setFields(CreateListingState state) {
        String formattedPrice = listingPriceFormat.format(state.getListingPrice());
        listingPriceInputField.setText(formattedPrice);
    }
}
