package view;
import interface_adapter.ViewManagerModel;
import interface_adapter.book_info.BookInfoController;
import interface_adapter.book_search.*;
import entity.Book;
import interface_adapter.search_filter.SearchFilterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;


import javax.imageio.ImageIO;
public class BookSearchView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "book search";
    private JTextField searchField;
    private JButton searchButton;
    private JButton filterButton;
    private JButton profileButton;
    private JPanel resultsPanel;
    private JButton loadMoreButton;
    private int currentLoadIndex = 0;
    private BookSearchController controller;
    private BookSearchViewModel viewModel;
    private SearchFilterController filterController;
    private BookInfoController bookInfoController;
    private ViewManagerModel viewManagerModel;
    private ArrayList<Book> displayedBooks;

    /**
     * Creates a new Book Search View with the given parameters
     * @param controller Book Search Controller
     * @param viewModel Book Search View Model
     * @param searchFilterController Search Filter Controller
     * @param bookInfoController Book Info Controller
     * @param viewManagerModel View Manager Model
     */
    public BookSearchView(BookSearchController controller, BookSearchViewModel viewModel, SearchFilterController searchFilterController, BookInfoController bookInfoController, ViewManagerModel viewManagerModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.filterController = searchFilterController;
        this.bookInfoController = bookInfoController;
        this.viewManagerModel = viewManagerModel;
        this.displayedBooks = new ArrayList<>();
        viewModel.addPropertyChangeListener(this);

        createUI();
    }

    /**
     * Creates the UI for the main page, which contains the search bar, search button, filter button, and profile button
     */
    private void createUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));

        Color Brown = new Color(217, 196, 152);
        Color lightBrown = new Color(245, 229, 196);
        Color whiteBrown = new Color(224, 218, 213);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Book Search");
        titlePanel.add(titleLabel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setBackground(lightBrown);
        topPanel.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);

        ImageIcon logoIcon = new ImageIcon("bookshelf.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setPreferredSize(new Dimension(150, 150));

        JTextArea paragraphText = new JTextArea("Welcome to our second-hand book selling application! " +
                "Enter a book title, author, or keyword to start searching. Please make sure there are no typos, and " +
                "press the Search button when you are ready. You can use the Filter button to filter your search once " +
                "results are displayed.", 5, 20);

        Font font = new Font("SansSerif", Font.PLAIN, 14);
        paragraphText.setFont(font);

        JPanel topComponentsPanel = new JPanel();
        topComponentsPanel.setLayout(new BorderLayout());
        topComponentsPanel.setBackground(lightBrown);
        topComponentsPanel.add(logoLabel, BorderLayout.NORTH);

        paragraphText.setEditable(false);
        paragraphText.setWrapStyleWord(true);
        paragraphText.setLineWrap(true);
        paragraphText.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        paragraphText.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        paragraphText.setForeground(Color.BLACK);
        paragraphText.setBackground(lightBrown);

        profileButton = new JButton("Profile");
        profileButton.setBackground(Brown);

        int topPadding = 20;
        int leftPadding = 20;
        int bottomPadding = 20;
        int rightPadding = 20;
        paragraphText.setBorder(BorderFactory.createEmptyBorder(
                topPadding, leftPadding, bottomPadding, rightPadding));

        topPanel.add(logoLabel, BorderLayout.NORTH);
        JPanel paragraphPanel = new JPanel();
        paragraphPanel.setLayout(new BoxLayout(paragraphPanel, BoxLayout.Y_AXIS));
        paragraphPanel.setBackground(lightBrown);
        paragraphPanel.add(paragraphText);
        paragraphPanel.add(Box.createVerticalStrut(10));
        paragraphPanel.add(profileButton);

        topComponentsPanel.add(paragraphPanel, BorderLayout.CENTER);
        topPanel.add(topComponentsPanel, BorderLayout.CENTER);

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
        profileButton.addActionListener(this);

        JPanel searchComponentsPanel = new JPanel(new BorderLayout());

        searchComponentsPanel.add(searchField, BorderLayout.CENTER);
        searchComponentsPanel.add(searchButton, BorderLayout.EAST);
        searchComponentsPanel.add(filterButton, BorderLayout.WEST);

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

        loadMoreButton.setVisible(false);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(lightBrown);

        JScrollPane resultsScrollPane = new JScrollPane(resultsPanel);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(resultsScrollPane, BorderLayout.CENTER);

        JPanel loadMorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loadMorePanel.setBackground(lightBrown);
        loadMorePanel.add(loadMoreButton);
        mainPanel.add(loadMorePanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            controller.onSearchButtonClicked(searchField.getText());
            boolean resultsAvailable = checkResultsAvailability();
            loadMoreButton.setVisible(resultsAvailable);
        }

        if (e.getSource() == profileButton){
            viewManagerModel.setActiveView("profile");
            viewManagerModel.firePropertyChanged();
            System.out.println(viewManagerModel.getActiveView());
        }

        if (e.getSource() == filterButton) {
            Color Brown = new Color(217, 196, 152);
            Color lightBrown = new Color(245, 229, 196);
            Color whiteBrown = new Color(224, 218, 213);
            JDialog filterDialog = new JDialog();
            filterDialog.setBackground(lightBrown);
            filterDialog.setTitle("Filter Options");

            JTextField authorFilter = new JTextField(20);
            authorFilter.setBackground(whiteBrown);
            JTextField yearFilter = new JTextField(10);
            yearFilter.setBackground(whiteBrown);
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
                    String authorValue = authorFilter.getText();
                    String yearValue = yearFilter.getText();
                    String hasListingsValue = (String) hasListingsFilter.getSelectedItem();
                    filterController.onFilterButtonClicked(authorValue, yearValue, hasListingsValue, displayedBooks);
                    filterDialog.dispose();
                }
            });

            JPanel filterPanel = new JPanel(new GridLayout(4, 2));
            filterPanel.setBackground(lightBrown);
            filterPanel.add(new JLabel("Author:"));
            filterPanel.add(authorFilter);
            filterPanel.add(new JLabel("Year:"));
            filterPanel.add(yearFilter);
            filterPanel.add(new JLabel("Has Listings:"));
            filterPanel.add(hasListingsFilter);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            buttonPanel.setBackground(lightBrown);
            buttonPanel.add(applyFilterButton);
            buttonPanel.add(cancelButton);

            filterPanel.add(buttonPanel, BorderLayout.SOUTH);
            filterDialog.add(filterPanel);
            filterDialog.pack();
            filterDialog.setLocationRelativeTo(this);
            filterDialog.setVisible(true);
        }
    }

    /**
     * Returns whether there are still Book results to display when the "load more" button is clicked
     * @return A boolean corresponding to whether there are still Book results to display when the "load more" button
     * is clicked
     */
    public boolean checkResultsAvailability() {
        List<Book> searchResults = viewModel.getState().getSearchResults();
        if (searchResults != null && !searchResults.isEmpty()) {
            int maxResultsToShow = 10;
            return searchResults.size() > maxResultsToShow;
        }
        return false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateResultsPanel(viewModel.getState().getSearchResults());
        }
    }

    /**
     * Populate the space beneath the search bar with 20 Book results once a search is made
     * @param books List of Books to display
     */
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
                addBookToPanel(book);

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

    /**
     * Display 20 more Books beneath the currently displayed Books
     */
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
        loadMoreButton.setVisible(currentLoadIndex < books.size());
    }

    /**
     * Creates a JPanel for a specific Book with that Book's cover, title, author, and year
     * @param book Book to create a panel for
     */
    private void addBookToPanel(Book book) {
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BorderLayout());
        JLabel coverLabel = new JLabel();

        if (book.getCoverUrl() != null) {
            try {
                URL coverUrl = new URL(book.getCoverUrl());
                HttpURLConnection connection = (HttpURLConnection) coverUrl.openConnection();
                connection.setInstanceFollowRedirects(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                ImageIcon coverImage = new ImageIcon(ImageIO.read(inputStream));
                coverImage.setImage(coverImage.getImage().getScaledInstance(120, 140, Image.SCALE_DEFAULT));
                coverLabel.setIcon(coverImage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ImageIcon defaultCoverImage = new ImageIcon("default.png");
            defaultCoverImage.setImage(defaultCoverImage.getImage().getScaledInstance(120, 140, Image.SCALE_DEFAULT));
            coverLabel.setIcon(defaultCoverImage);
        }

        bookPanel.add(coverLabel, BorderLayout.WEST);
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(3, 1));

        JLabel titleLabel = new JLabel("Title: " + book.getTitle());
        JLabel authorLabel = new JLabel("Author: " + book.getAuthor());
        JLabel yearLabel = new JLabel("Publication Year: " + book.getYear());

        detailsPanel.add(titleLabel);
        detailsPanel.add(authorLabel);
        detailsPanel.add(yearLabel);

        bookPanel.add(detailsPanel, BorderLayout.CENTER);

        resultsPanel.add(bookPanel);

        bookPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bookInfoController.onBookSelected(book.getTitle(), book.getYear(), book.getAuthor(), book.getISBN(), book.getCoverUrl(), book.getLanguage(), book.getSubjects());
            }
        });

    }
}