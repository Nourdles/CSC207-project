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
public class BookSearchView extends JFrame implements ActionListener, PropertyChangeListener{
    private JTextField searchField;
    private JButton searchButton;
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
        setTitle("Book Search");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Search panel setup
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        Color Brown = new Color(217, 196, 152);
        Color lightBrown = new Color(245, 229, 196);
        Color whiteBrown = new Color(224, 218, 213);
        searchButton.setBackground(Brown);
        searchPanel.setBackground(lightBrown);
        searchField.setBackground(whiteBrown);

        // Results panel setup
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        resultsPanel.setBackground(lightBrown);

        // Adding the search panel and results panel to the frame
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(resultsPanel), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            controller.onSearchButtonClicked(searchField.getText());
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
        JOptionPane.showMessageDialog(this, "Details for: " + book.getTitle());
    }

    public void display() {
        setVisible(true);
    }
}
