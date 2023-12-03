package view;

import entity.Book;
import interface_adapter.booksearch.BookSearchController;
import interface_adapter.booksearch.BookSearchViewModel;
import interface_adapter.searchfilter.SearchFilterController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookSearchView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "book search";
    private JTextField searchField;
    private JButton searchButton;
    private JButton filterButton;
    private JPanel resultsPanel;
    private JButton loadMoreButton;
    private int currentLoadIndex = 0; // To keep track of how many books have been loaded
    private BookSearchController controller;
    private BookSearchViewModel viewModel;
    private SearchFilterController filterController;
    private ArrayList<Book> displayedBooks;

    public BookSearchView(BookSearchController controller, BookSearchViewModel viewModel, SearchFilterController searchFilterController) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.filterController = searchFilterController;
        this.displayedBooks = new ArrayList<>();
        viewModel.addPropertyChangeListener(this);

        createUI();
    }

    private void createUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600)); // Set the preferred size of the panel

        Color Brown = new Color(217, 196, 152);
        Color lightBrown = new Color(245, 229, 196);
        Color whiteBrown = new Color(224, 218, 213);

        // Create a panel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Book Search");
        titlePanel.add(titleLabel);



        // Main content panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top panel setup
        JPanel topPanel = new JPanel();
        topPanel.setBackground(lightBrown);
        topPanel.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);

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

        loadMoreButton = new JButton("Load More");
        loadMoreButton.setBackground(Brown);
        loadMoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMoreBooks();
            }
        });

        // Set initial visibility of the load more button to false
        loadMoreButton.setVisible(false);

        // Results panel setup
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(lightBrown);

        // Encapsulate the resultsPanel within a JScrollPane
        JScrollPane resultsScrollPane = new JScrollPane(resultsPanel);

        // Adding the topPanel and search panel to the main panel
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(resultsScrollPane, BorderLayout.CENTER);

        JPanel loadMorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loadMorePanel.setBackground(lightBrown);
        loadMorePanel.add(loadMoreButton);
        mainPanel.add(loadMorePanel, BorderLayout.SOUTH);

        // Adding components directly to 'this' (the BookSearchView panel)
        this.add(mainPanel, BorderLayout.CENTER); // Add the main panel to the top

        // Ensure that the components are visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            controller.onSearchButtonClicked(searchField.getText());
            boolean resultsAvailable = checkResultsAvailability();

            // Set the visibility of the load more button based on results availability
            loadMoreButton.setVisible(resultsAvailable);
        }

        if (e.getSource() == filterButton) {
            Color Brown = new Color(217, 196, 152);
            Color lightBrown = new Color(245, 229, 196);
            Color whiteBrown = new Color(224, 218, 213);
            JDialog filterDialog = new JDialog();
            filterDialog.setBackground(lightBrown);
            filterDialog.setTitle("Filter Options");

            // Create UI components for author, year, and "has listings" filters
            JTextField authorFilter = new JTextField(20);
            authorFilter.setBackground(lightBrown);
            JTextField yearFilter = new JTextField(10);
            yearFilter.setBackground(lightBrown);
            JComboBox<String> hasListingsFilter = new JComboBox<>(new String[]{"Both", "No", "Yes"});
            hasListingsFilter.setBackground(lightBrown);
            JButton applyFilterButton = new JButton("Apply Filters");
            applyFilterButton.setBackground(Brown);
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBackground(Brown);

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filterDialog.dispose();
                }
            });

            applyFilterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the selected filter values
                    String authorValue = authorFilter.getText();
                    String yearValue = yearFilter.getText();
                    String hasListingsValue = (String) hasListingsFilter.getSelectedItem();

                    // Call the controller or delegate to handle filter logic
                    filterController.onFilterButtonClicked(authorValue, yearValue, hasListingsValue, displayedBooks);

                    // Close the filter dialog
                    filterDialog.dispose();
                }
            });

            JPanel filterPanel = new JPanel(new GridLayout(4, 2));
            filterPanel.add(new JLabel("Author:"));
            filterPanel.add(authorFilter);
            filterPanel.add(new JLabel("Year:"));
            filterPanel.add(yearFilter);
            filterPanel.add(new JLabel("Has Listings:"));
            filterPanel.add(hasListingsFilter);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            buttonPanel.add(applyFilterButton);
            buttonPanel.add(cancelButton);

            filterPanel.add(buttonPanel, BorderLayout.SOUTH);

            filterDialog.add(filterPanel);

            // Set dialog properties and display
            filterDialog.pack();
            filterDialog.setLocationRelativeTo(this); // Center the dialog relative to the main window
            filterDialog.setVisible(true);
        }
    }

    public boolean checkResultsAvailability() {
        List<Book> searchResults = viewModel.getState().getSearchResults();
        // Check if there are more results available
        if (searchResults != null && !searchResults.isEmpty()) {
            // You can define a threshold to decide whether to show the "Load More" button
            int maxResultsToShow = 10; // Set your threshold here

            // Check if there are more results beyond the threshold
            return searchResults.size() > maxResultsToShow;
        }

        return false; // No results available
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateResultsPanel(viewModel.getState().getSearchResults());
        }
    }

    public void loadInitialSearchResults(ArrayList<Book> searchResults) {
        this.displayedBooks.addAll(searchResults);
    }

    private void updateResultsPanel(List<Book> books) {
        resultsPanel.removeAll();
        currentLoadIndex = 0;
        displayedBooks.clear();

        if (books != null && !books.isEmpty()) {
            int displayLimit = Math.min(books.size(), 20);
            for (int i = 0; i < displayLimit; i++) {
                Book book = books.get(i);
                displayedBooks.add(book);

                currentLoadIndex++;

                // Create a custom JPanel for each book entry
                addBookToPanel(book);

                // Add a separator after each bookPanel except the last one
                if (i < displayLimit - 1) {
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

    private void loadMoreBooks() {
        List<Book> books = viewModel.getState().getSearchResults();
        if (books == null) {
            return;
        }

        int nextLoadIndex = Math.min(currentLoadIndex + 10, books.size());
        for (int i = currentLoadIndex; i < nextLoadIndex; i++) {
            addBookToPanel(books.get(i));
        }

        currentLoadIndex = nextLoadIndex;
        // Hide the load more button if there are no more books to load
        loadMoreButton.setVisible(currentLoadIndex < books.size());
    }

    private void addBookToPanel(Book book) {
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

        // Add the custom bookPanel to the resultsPanel
        resultsPanel.add(bookPanel);
    }


    public void display() {
        setVisible(true);
    }
}
