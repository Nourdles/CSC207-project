package use_case.book_info;

import java.util.List;
import java.util.ArrayList;

public class BookInfoOutputData {
    private final String title;
    private final int year;
    private final String author;
    private final String ISBN;
    private final String coverURL;
    private final String language;
    private final ArrayList<String> subjects;
    private final List<List<String>> listingsDetails;
    private final String errorMessage;

    /**
     * Creates a new Book Info Output Data with the given parameters
     * @param title String that represents the title of the chosen Book
     * @param year int that represents the title of the chosen Book
     * @param author String that represents the author of the chosen Book
     * @param isbn String that represents the ISBN of the chosen Book
     * @param coverURL String that represents the url for the cover of the chosen Book
     * @param language String that represents the language of the chosen Book
     * @param subjects ArrayList that represents the subjects of the chosen Book
     * @param listingsDetails List of Lists that represents the information of all the listings of the chosen Book
     * @param errorMessage String that represents the message to be displayed if an error occurs
     */
    public BookInfoOutputData(String title, int year, String author, String isbn, String coverURL, String language, ArrayList<String> subjects, List<List<String>> listingsDetails, String errorMessage) {
        this.title = title;
        this.year = year;
        this.author = author;
        ISBN = isbn;
        this.coverURL = coverURL;
        this.language = language;
        this.subjects = subjects;
        this.listingsDetails = listingsDetails;
        this.errorMessage = errorMessage;
    }

    /**
     * Returns whether no error has occurred
     * @return A boolean that represents whether no error has occurred
     */
    public boolean isSuccess() {
        return errorMessage == null;
    }

    /**
     * Returns the subjects of the chosen Book if no error occurred, otherwise throw an Exception
     * @return ArrayList that represents the subjects of the chosen Book
     */
    public ArrayList<String> getSubjects() {
        if (isSuccess()) {
            return this.subjects;
        } else {
            throw new IllegalStateException("Attempted to get subjects when the display was not successful.");
        }
    }

    /**
     * Returns the title of the chosen Book if no error occurred, otherwise throw an Exception
     * @return String that represents the title of the chosen Book
     */
    public String getTitle() {
        if (isSuccess()) {
            return this.title;
        } else {
            throw new IllegalStateException("Attempted to get title when the display was not successful.");
        }
    }

    /**
     * Returns the information on all the Listings of the chosen Book
     * @return List of Lists representing the information on all the Listings of the chosen Book
     */
    public List<List<String>> getListingsDetails() {return listingsDetails;}

    /**
     * Returns the author of the chosen Book if no error occurred, otherwise throw an Exception
     * @return String that represents the author of the chosen Book
     */
    public String getAuthor() {
        if (isSuccess()) {
            return this.author;
        } else {
            throw new IllegalStateException("Attempted to get author when the display was not successful.");
        }
    }

    /**
     * Returns the url of the cover of the chosen Book if no error occurred, otherwise throw an Exception
     * @return String that represents the url of the cover of the chosen Book
     */
    public String getCoverURL() {
        if (isSuccess()) {
            return this.coverURL;
        } else {
            throw new IllegalStateException("Attempted to get cover URL when the display was not successful.");
        }
    }

    /**
     * Returns the ISBN of the chosen Book if no error occurred, otherwise throw an Exception
     * @return String that represents the ISBN of the chosen Book
     */
    public String getISBN() {
        if (isSuccess()) {
            return this.ISBN;
        } else {
            throw new IllegalStateException("Attempted to get ISBN when the display was not successful.");
        }
    }

    /**
     * Returns the language of the chosen Book if no error occurred, otherwise throw an Exception
     * @return String that represents the language of the chosen Book
     */
    public String getLanguage() {
        if (isSuccess()) {
            return this.language;
        } else {
            throw new IllegalStateException("Attempted to get language when the display was not successful.");
        }
    }

    /**
     * Returns the year of the chosen Book if no error occurred, otherwise throw an Exception
     * @return int that represents the year of the chosen Book
     */
    public int getYear() {
        if (isSuccess()) {
            return this.year;
        } else {
            throw new IllegalStateException("Attempted to get year when the display was not successful.");
        }
    }

    /**
     * Returns the error message if an error occurred, otherwise throw an Exception
     * @return String that represents the error message
     */
    public String getErrorMessage() {
        if (!isSuccess()) {
            return errorMessage;
        } else {
            throw new IllegalStateException("Attempted to get error message when the display was successful.");
        }
    }
}