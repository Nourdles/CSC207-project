package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.book_info.BookInfoState;
import interface_adapter.book_info.BookInfoViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class BookInfoView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "book info";
    private final BookInfoViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private JButton backButton;
    private JButton createListingButton;
    private JLabel titleLabel, isbnLabel, authorLabel, yearLabel, languageLabel, coverLabel;
    private JPanel subjectsPanel;
    private JPanel listingsPanel;

    public BookInfoView(BookInfoViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);
        viewManagerModel.addPropertyChangeListener(this);
        createUI();
    }

    private void createUI() {
        setLayout(new BorderLayout());
        Color Brown = new Color(217, 196, 152);
        Color lightBrown = new Color(245, 229, 196);
        Color whiteBrown = new Color(224, 218, 213);

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setBackground(Brown);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(whiteBrown);
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel coverPanel = new JPanel();
        coverPanel.setBackground(lightBrown);
        coverLabel = new JLabel();
        coverPanel.add(coverLabel);
        coverPanel.setPreferredSize(new Dimension(400, 600));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(10, 10, 1, 10); // Padding
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0.5;
        mainPanel.add(coverPanel, gbc);

        JPanel infoPanel = new JPanel(new GridLayout(6, 1));
        infoPanel.setBackground(lightBrown);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        isbnLabel = new JLabel("ISBN: ");
        authorLabel = new JLabel("Author: ");
        yearLabel = new JLabel("Year: ");
        languageLabel = new JLabel("Languages: ");
        infoPanel.add(isbnLabel);
        infoPanel.add(authorLabel);
        infoPanel.add(yearLabel);
        infoPanel.add(languageLabel);

        subjectsPanel = new JPanel();
        subjectsPanel.setBackground(lightBrown);
        subjectsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.add(new JLabel("Subjects:"));
        infoPanel.add(subjectsPanel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.8;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(infoPanel, gbc);
        add(mainPanel, BorderLayout.CENTER);

        JPanel listingsPanel = new JPanel(new BorderLayout());
        listingsPanel.setBackground(whiteBrown);
        JLabel listingsTitle = new JLabel("Listings");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 1.2;
        listingsPanel.add(listingsTitle, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(lightBrown);
        createListingButton = new JButton("Create Listing");
        createListingButton.setBackground(Brown);
        createListingButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.setBackground(Brown);
        backButton.addActionListener(this);
        buttonPanel.add(createListingButton);
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(listingsPanel, gbc);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            viewManagerModel.setActiveView("book search");
            viewManagerModel.firePropertyChanged();
        } else if (e.getSource() == createListingButton) {

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateUI(viewModel.getState());
        }
    }

    private void updateUI(BookInfoState state) {
        titleLabel.setText(state.getTitle());
        isbnLabel.setText("ISBN: " + state.getISBN());
        authorLabel.setText("Author: " + state.getAuthor());
        yearLabel.setText("Year: " + state.getYear());
        languageLabel.setText("Language: " + state.getLanguage());
        if (state.getCoverURL() != null) {
            try {
                URL coverUrl = new URL(state.getCoverURL());

                HttpURLConnection connection = (HttpURLConnection) coverUrl.openConnection();
                connection.setInstanceFollowRedirects(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                ImageIcon coverImage = new ImageIcon(ImageIO.read(inputStream));
                coverImage.setImage(coverImage.getImage().getScaledInstance(220, 300, Image.SCALE_DEFAULT));
                coverLabel.setIcon(coverImage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ImageIcon defaultCoverImage = new ImageIcon("default.png");
            defaultCoverImage.setImage(defaultCoverImage.getImage().getScaledInstance(220, 300, Image.SCALE_DEFAULT));
            coverLabel.setIcon(defaultCoverImage);
        };

        String subjectsText = String.join(", ", state.getSubjects());
        subjectsPanel.removeAll();
        subjectsPanel.add(new JLabel(subjectsText));

        revalidate();
        repaint();
    }

}
