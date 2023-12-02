package view;
import interface_adapter.booksearch.*;
import entity.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;


import javax.imageio.ImageIO;
public class BookSearchView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "book search";
    private JTextField searchField;
    private JButton searchButton;
    private JButton filterButton;
    private JPanel resultsPanel;
    private BookSearchController controller;
    private BookSearchViewModel viewModel;

    public BookSearchView(BookSearchController controller, BookSearchViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        createUI();
    }

    private void createUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600)); // Set the preferred size of the panel

        // Create a panel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create a JLabel for the title
        JLabel titleLabel = new JLabel("Book Search");
        titlePanel.add(titleLabel);

        // Add the title panel to the top (NORTH) of the panel
        this.add(titlePanel, BorderLayout.NORTH);

        Color Brown = new Color(217, 196, 152);
        Color lightBrown = new Color(245, 229, 196);
        Color whiteBrown = new Color(224, 218, 213);

        // Main content panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top panel setup
        JPanel topPanel = new JPanel();
        topPanel.setBackground(lightBrown);
        topPanel.setLayout(new BorderLayout());

        // Add our logo as an ImageIcon to a JLabel
        ImageIcon logoIcon = new ImageIcon("bookshelf.png"); // Replace with the path to your logo image
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setPreferredSize(new Dimension(150, 150)); // Set the preferred size (adjust as needed)

        // Create a JTextArea for our search instructions
        JTextArea paragraphText = new JTextArea("Welcome to our second-hand book selling application! " +
                "Enter a book title, author, or keyword to start searching. Please make sure there are no typos, and " +
                "press the Search button when you are ready. You can use the Filter button to filter your search once " +
                "results are displayed.");

        Font font = new Font("SansSerif", Font.PLAIN, 14); // You can adjust the font and size
        paragraphText.setFont(font);

        paragraphText.setEditable(false);
        paragraphText.setWrapStyleWord(true);
        paragraphText.setLineWrap(true);
        paragraphText.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        paragraphText.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        paragraphText.setForeground(Color.BLACK);
        paragraphText.setBackground(lightBrown);

        // Create an EmptyBorder to add space around the paragraph text
        int topPadding = 20; // Adjust the top padding as needed
        int leftPadding = 20; // Adjust the left padding as needed
        int bottomPadding = 20; // Adjust the bottom padding as needed
        int rightPadding = 20; // Adjust the right padding as needed
        paragraphText.setBorder(BorderFactory.createEmptyBorder(
                topPadding, leftPadding, bottomPadding, rightPadding));

        // Add the logo to the topPanel
        topPanel.add(logoLabel, BorderLayout.NORTH);

        // Add the paragraphText to the topPanel at the SOUTH position
        topPanel.add(paragraphText, BorderLayout.SOUTH);

        // Search panel setup
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(lightBrown);

        ImageIcon searchImage = new ImageIcon("search.png");
        searchImage.setImage(searchImage.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

        searchField = new JTextField(40);
        searchButton = new JButton(searchImage);
        filterButton = new JButton("Filters");
        searchButton.addActionListener(this);
        filterButton.addActionListener(this);

        JPanel searchComponentsPanel = new JPanel(new BorderLayout());

        searchComponentsPanel.add(searchField, BorderLayout.CENTER);
        searchComponentsPanel.add(searchButton, BorderLayout.EAST);
        searchComponentsPanel.add(filterButton, BorderLayout.WEST);


        // Add the search components panel to the search panel
        searchPanel.add(searchComponentsPanel, BorderLayout.NORTH);

        searchButton.setBackground(Brown);
        filterButton.setBackground(Brown);
        searchField.setBackground(whiteBrown);

        // Results panel setup
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(lightBrown);

        // Encapsulate the resultsPanel within a JScrollPane
        JScrollPane resultsScrollPane = new JScrollPane(resultsPanel);

        // Adding the topPanel and search panel to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.CENTER);

        // Adding components directly to 'this' (the BookSearchView panel)
        this.add(mainPanel, BorderLayout.NORTH); // Add the main panel to the top
        this.add(resultsScrollPane, BorderLayout.CENTER); // Add the results scroll pane to the center

        // Ensure that the components are visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            controller.onSearchButtonClicked(searchField.getText());
        }

        if (e.getSource() == filterButton) {
            JOptionPane.showMessageDialog(this, "Filter options:");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateResultsPanel(viewModel.getState().getSearchResults());
        }
    }

    private void updateResultsPanel(List<Book> books) {
        resultsPanel.removeAll();

        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
                // Create a custom JPanel for each book entry
                JPanel bookPanel = new JPanel();
                bookPanel.setLayout(new BorderLayout());

                // Create a JLabel for the book cover image
                JLabel coverLabel = new JLabel();

                if (book.getCoverUrl() != null) {
                    try {
                        URL coverUrl = new URL(book.getCoverUrl());
                        // Configure HttpURLConnection to follow redirects
                        HttpURLConnection connection = (HttpURLConnection) coverUrl.openConnection();
                        connection.setInstanceFollowRedirects(true);
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();

                        ImageIcon coverImage = new ImageIcon(ImageIO.read(inputStream));
                        // Set a preferred size for the cover image
                        coverImage.setImage(coverImage.getImage().getScaledInstance(120, 140, Image.SCALE_DEFAULT));
                        coverLabel.setIcon(coverImage);
                    } catch (MalformedURLException e) {
                        e.printStackTrace(); // Handle the exception appropriately or log it
                    } catch (IOException e) {
                        e.printStackTrace(); // Handle IO exceptions
                    }
                } else {
                    // Set a default image when no cover URL is available
                    ImageIcon defaultCoverImage = new ImageIcon("default.png"); // Replace with your default image path
                    defaultCoverImage.setImage(defaultCoverImage.getImage().getScaledInstance(120, 140, Image.SCALE_DEFAULT));
                    coverLabel.setIcon(defaultCoverImage);
                }

                bookPanel.add(coverLabel, BorderLayout.WEST);

                // Create a JPanel to hold book details (title, author, year)
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new GridLayout(3, 1));

                // Create JLabels for book details
                JLabel titleLabel = new JLabel("Title: " + book.getTitle());
                JLabel authorLabel = new JLabel("Author: " + book.getAuthor());
                JLabel yearLabel = new JLabel("Publication Year: " + book.getYear());

                // Add book details JLabels to the detailsPanel
                detailsPanel.add(titleLabel);
                detailsPanel.add(authorLabel);
                detailsPanel.add(yearLabel);

                // Add the detailsPanel to the bookPanel
                bookPanel.add(detailsPanel, BorderLayout.CENTER);

                // Add a mouse listener for the click event
                bookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Make it look clickable
                bookPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Open a new view or dialog with more information about the book
                        showBookDetails(book);
                    }
                });

                // Add the custom bookPanel to the resultsPanel
                resultsPanel.add(bookPanel);

                // Add a separator after each bookPanel except the last one
                if (books.indexOf(book) < books.size() - 1) {
                    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
                    resultsPanel.add(separator);
                }
            }
        } else {
            resultsPanel.add(new JLabel("No results found or there was an error."));
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }





    private void showBookDetails(Book book) {
        // Implement logic to display book details
        // This could open a new JFrame or JDialog with the book's details
        JOptionPane.showInputDialog(this, "Details for: " + book.getTitle());
    }


    public void display() {
        setVisible(true);
    }
}
