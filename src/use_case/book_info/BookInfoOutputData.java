package use_case.book_info;

import java.util.ArrayList;

public class BookInfoOutputData {
    private final String title;
    private final int year;
    private final String author;
    private final String ISBN;
    private final String coverURL;
    private final String language;
    private final ArrayList<String> subjects;
    private final String errorMessage;

    public BookInfoOutputData(String title, int year, String author, String isbn, String coverURL, String language, ArrayList<String> subjects, String errorMessage) {
        this.title = title;
        this.year = year;
        this.author = author;
        ISBN = isbn;
        this.coverURL = coverURL;
        this.language = language;
        this.subjects = subjects;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return errorMessage == null;
    }

    public ArrayList<String> getSubjects() {
        if (isSuccess()) {
            return this.subjects;
        } else {
            throw new IllegalStateException("Attempted to get subjects when the display was not successful.");
        }
    }

    public String getTitle() {
        if (isSuccess()) {
            return this.title;
        } else {
            throw new IllegalStateException("Attempted to get title when the display was not successful.");
        }
    }

    public String getAuthor() {
        if (isSuccess()) {
            return this.author;
        } else {
            throw new IllegalStateException("Attempted to get author when the display was not successful.");
        }
    }

    public String getCoverURL() {
        if (isSuccess()) {
            return this.coverURL;
        } else {
            throw new IllegalStateException("Attempted to get cover URL when the display was not successful.");
        }
    }

    public String getISBN() {
        if (isSuccess()) {
            return this.ISBN;
        } else {
            throw new IllegalStateException("Attempted to get ISBN when the display was not successful.");
        }
    }

    public String getLanguage() {
        if (isSuccess()) {
            return this.language;
        } else {
            throw new IllegalStateException("Attempted to get language when the display was not successful.");
        }
    }

    public int getYear() {
        if (isSuccess()) {
            return this.year;
        } else {
            throw new IllegalStateException("Attempted to get year when the display was not successful.");
        }
    }

    public String getErrorMessage() {
        if (!isSuccess()) {
            return errorMessage;
        } else {
            throw new IllegalStateException("Attempted to get error message when the display was successful.");
        }
    }
}
