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

    public Book(String title, int year, String author, String summary, String ISBN, int inStock, String coverUrl,
                String language, ArrayList<String> subjects) {

    }

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
     */
    public Book(String title, int year, String author, String summary, String ISBN, int inStock, String coverUrl
                ) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public int getInStock() {
        return inStock;
    }
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
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
    public String getCoverUrl() {
        return this.coverUrl;
    }
    public int getEverInStock(){ return this.everInStock; }
    public void setEverInStock(){
        this.everInStock = this.everInStock++;
    }
    public String getLanguage() {return this.language;}
    public ArrayList<String> getSubjects(){return this.subjects;}
}


