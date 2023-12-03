package interface_adapter.book_info;

import java.util.ArrayList;

public class BookInfoState {
    private String title;
    private int year;
    private String author;
    private String ISBN;
    private String coverURL;
    private String language;
    private ArrayList<String> subjects;
    private String errorMessage = null;

    public BookInfoState() {}
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setYear(int year) {this.year = year;}

    public void setTitle(String title) {this.title = title;}

    public void setAuthor(String author) {this.author = author;}

    public void setISBN(String ISBN) {this.ISBN = ISBN;}

    public void setCoverURL(String coverURL) {this.coverURL = coverURL;}

    public void setLanguage(String language) {this.language = language;}

    public void setSubjects(ArrayList<String> subjects) {this.subjects = subjects;}

    public String getTitle() {return this.title;}

    public String getAuthor() {return this.author;}

    public int getYear() {return this.year;}

    public String getISBN() {return this.ISBN;}

    public String getLanguage() {return this.language;}

    public String getCoverURL() {return this.coverURL;}

    public ArrayList<String> getSubjects() {return this.subjects;}
}
