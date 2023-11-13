package src.view;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class represents a search bar component that can be added to a graphical user interface (GUI).
 */
public class SearchBar extends JPanel {
    private JTextField searchField;
    private JButton searchButton;

    /**
     * Constructor to create a search bar component.
     */
    public SearchBar() {
        // Set layout for the search bar panel
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create the search field
        searchField = new JTextField(20);
        add(searchField);

        // Create the search button
        searchButton = new JButton("Search");
        add(searchButton);
    }

    /**
     * Getter for the search field text.
     *
     * @return Returns the text entered in the search field.
     */
    public String getSearchText() {
        return searchField.getText();
    }

    /**
     * Setter for the search field text.
     *
     * @param searchText The text to be set in the search field.
     */
    public void setSearchText(String searchText) {
        searchField.setText(searchText);
    }

    /**
     * Adds an ActionListener to the search button.
     *
     * @param listener The ActionListener to be added to the search button.
     */
    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    /**
     * Removes an ActionListener from the search button.
     *
     * @param listener The ActionListener to be removed from the search button.
     */
    public void removeSearchButtonListener(ActionListener listener) {
        searchButton.removeActionListener(listener);
    }

    /**
     * Main method to test the SearchBar component.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create a JFrame to hold the search bar
        JFrame frame = new JFrame("Search Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        // Create a search bar
        SearchBar searchBar = new SearchBar();

        // Add the search bar to the frame
        frame.getContentPane().add(searchBar);

        // Display the frame
        frame.setVisible(true);
    }
}
