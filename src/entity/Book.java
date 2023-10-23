package src.entity;

public class Book {

    private String title;
    private String genre;
    private int year;
    private String author;
    private String summary;
    private int ISBN;
    private int inStock;

    Book(String title, String genre, int year, String author, String summary, int ISBN, int inStock) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.author = author;
        this.summary = summary;
        this.ISBN = ISBN;
        this.inStock = inStock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}
