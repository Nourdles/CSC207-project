package entity;

import java.util.ArrayList;

public class Book {
    private int everInStock;
    private String title;
    private int year;
    private String author;
    private String summary;
    private String ISBN;
    private int inStock;
    private String coverUrl;
    private String language;
    private ArrayList<String> subjects;

    /**
     * A book listed in the OpenLibrary database.
     * @param title
     * @param year year published
     * @param author
     * @param summary a description of the book.
     * @param ISBN a 13-digit number representing in characters uniquely assigned to a book, with
     *             different editions having different ISBNs.
     * @param inStock the number of listings currently available for a unique book.
     * @param coverUrl a url associated with a particular book cover from OpenLibrary.
     * @param language the language of the book.
     * @param subjects a list of the book's subjects.
     */
    public Book(String title, int year, String author, String summary, String ISBN, int inStock, String coverUrl, String language, ArrayList<String> subjects) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.summary = summary;
        this.ISBN = ISBN;
        this.inStock = inStock;
        this.coverUrl = coverUrl;
        this.language = language;
        this.subjects = subjects;
    }

    /**
     * Returns the title of the Book
     * @return The title of the Book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the Book to be the given String
     * @param title String we want to set the title to
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Returns the year of the Book
     * @return The year of the Book
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year of the Book to be the given int
     * @param year int we want to set the year to
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the author of the Book
     * @return The author of the Book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author of the Book to be the given String
     * @param author String we want to set the author to
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the summary of the Book
     * @return The summary of the Book
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Set the summary of the Book to be the given String
     * @param summary String we want to set the summary to
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Returns the ISBN of the Book
     * @return The ISBN of the Book
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Set the ISBN of the Book to be the given String
     * @param ISBN String we want to set the ISBN to
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Returns the amount of listings for the Book
     * @return The amount of listings for the Book
     */
    public int getInStock() {
        return inStock;
    }

    /**
     * Set the number of listings of the Book to be the given int
     * @param inStock int we want to set the amount in stock to
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    /**
     * Returns a string format to represent a Book and its details.
     * @return a String format representing a Book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", summary='" + summary + '\'' +
                ", ISBN=" + ISBN +
                ", inStock=" + inStock +
                ", coverUrl='" + coverUrl + '\'' +
                '}';
    }

    /**
     * Returns the url for the cover of the Book
     * @return The url for the cover of the Book
     */
    public String getCoverUrl() {
        return this.coverUrl;
    }

    /**
     * Returns the language of the Book
     * @return The language of the Book
     */
    public String getLanguage() {return this.language;}

    /**
     * Returns the subjects of the Book
     * @return The subjects of the Book
     */
    public ArrayList<String> getSubjects(){return this.subjects;}
}


