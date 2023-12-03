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

    public BookInfoInputData(String title, int year, String author, String isbn, String coverURL, String language, ArrayList<String> subjects) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.ISBN = isbn;
        this.coverURL = coverURL;
        this.language = language;
        this.subjects = subjects;
    }

    public String getTitle(){return this.title;}

    public int getYear(){return this.year;}

    public String getAuthor(){return this.author;}

    public String getISBN(){return this.ISBN;}
    public String getCoverURL(){return this.coverURL;}
    public String getLanguage(){return this.language;}
    public ArrayList<String> getSubjects(){return this.subjects;}
}
