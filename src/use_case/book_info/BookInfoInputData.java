package use_case.book_info;

import java.util.ArrayList;

public class BookInfoInputData {
    final private String title;
    final private int year;
    final private String author;
    final private String ISBN;
    final private String coverURL;
    final private String language;
    final private ArrayList<String> subjects;

    /**
     * Create a new Book Info Input Data with the given parameters
     * @param title String that represents the title of the chosen Book
     * @param year int that represents the title of the chosen Book
     * @param author String that represents the author of the chosen Book
     * @param isbn String that represents the ISBN of the chosen Book
     * @param coverURL String that represents the url for the cover of the chosen Book
     * @param language String that represents the language of the chosen Book
     * @param subjects ArrayList that represents the subjects of the chosen Book
     */
    public BookInfoInputData(String title, int year, String author, String isbn, String coverURL, String language, ArrayList<String> subjects) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.ISBN = isbn;
        this.coverURL = coverURL;
        this.language = language;
        this.subjects = subjects;
    }

    /**
     * Returns the title of the Input Data
     * @return String that represents the title of the Input Data
     */
    public String getTitle(){return this.title;}

    /**
     * Returns the year of the Input Data
     * @return int that represents the year of the Input Data
     */
    public int getYear(){return this.year;}

    /**
     * Returns the author of the Input Data
     * @return String that represents the author of the Input Data
     */
    public String getAuthor(){return this.author;}

    /**
     * Returns the ISBN of the Input Data
     * @return String that represents the ISBN of the Input Data
     */
    public String getISBN(){return this.ISBN;}

    /**
     * Returns the url of the cover of the Input Data
     * @return String that represents the url of the cover of the Input Data
     */
    public String getCoverURL(){return this.coverURL;}

    /**
     * Returns the language of the Input Data
     * @return String that represents the language of the Input Data
     */
    public String getLanguage(){return this.language;}

    /**
     * Returns the subjects of the Input Data
     * @return ArrayList that represents the subjects of the Input Data
     */
    public ArrayList<String> getSubjects(){return this.subjects;}
}
