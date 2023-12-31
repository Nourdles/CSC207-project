package interface_adapter.book_info;

import entity.Listing;

import java.util.ArrayList;
import java.util.List;

public class BookInfoState {
    private String title;
    private int year;
    private String author;
    private String ISBN;
    private String coverURL;
    private String language;
    private ArrayList<String> subjects;
    private List<List<String>> listingsInfo;
    private String errorMessage = "Could not retrieve book information";

    /**
     * Initialize a new State for the Book Info use case
     */
    public BookInfoState() {}

    /**
     * Sets the error message to the given String
     * @param errorMessage message to be displayed when the Book Info use case fails
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the error message in the current State
     * @return String that represents the error message in the current State
     */
    public String getErrorMessage() {return this.errorMessage;}

    /**
     * Sets the year for this State
     * @param year int we want to set the year of the State to
     */
    public void setYear(int year) {this.year = year;}

    /**
     * Sets the title for this State
     * @param title String we want to set the title of the State to
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * Sets the author for this State
     * @param author String we want to set the author of the State to
     */
    public void setAuthor(String author) {this.author = author;}

    /**
     * Sets the ISBN for this State
     * @param ISBN String we want to set the ISBN of the State to
     */
    public void setISBN(String ISBN) {this.ISBN = ISBN;}

    /**
     * Sets the coverl of the URL of the chosen Book for this State
     * @param coverURL String we want to set the url of the Book cover of the State to
     */
    public void setCoverURL(String coverURL) {this.coverURL = coverURL;}

    /**
     * Sets the language for this State
     * @param language String we want to set the language of the State to
     */
    public void setLanguage(String language) {this.language = language;}

    /**
     * Sets the subjects for this State
     * @param subjects list of Strings we want to set the subjects of the State to
     */
    public void setSubjects(ArrayList<String> subjects) {this.subjects = subjects;}

    /**
     * Returns the title of the State
     * @return The title of the State
     */
    public String getTitle() {return this.title;}

    /**
     * Returns the author of the State
     * @return The author of the State
     */
    public String getAuthor() {return this.author;}

    /**
     * Returns the year of the State
     * @return The year of the State
     */
    public int getYear() {return this.year;}

    /**
     * Returns the ISBN of the State
     * @return The ISBN of the State
     */
    public String getISBN() {return this.ISBN;}

    /**
     * Returns the language of the State
     * @return The language of the State
     */
    public String getLanguage() {return this.language;}

    /**
     * Returns the url of the Book cover of the State
     * @return The url of the Book cover of the State
     */
    public String getCoverURL() {return this.coverURL;}

    /**
     * Returns the subjects of the State
     * @return The subjects of the State
     */
    public ArrayList<String> getSubjects() {return this.subjects;}

    /**
     * Sets the listings info for the State
     * @param listingsInfo List of List of Strings containing info on the relevant listings
     */
    public void setListingsInfo(List<List<String>> listingsInfo) {this.listingsInfo = listingsInfo;}

    /**
     * Returns all the Book Listing information of the State
     * @return All the Book Listing information
     */
    public List<List<String>> getListingsInfo() {return listingsInfo;}

    /**
     * Return true if both BookInfoStates are equals (they have the same attributes).
     * Otherwise, return false.
     * @param obj
     * @return true if BookInfoStates have equivalent attributes. Else, return false.
     */
    public boolean equals(Object obj){
        if (!(obj instanceof BookInfoState)){
            return false;
        }

        BookInfoState state = (BookInfoState) (obj);
        if (state.title == null && this.title == null && state.author == null &&
                this.author == null && state.year == 0 && this.year == 0 &&
                state.ISBN == null && this.ISBN == null && state.coverURL == null &&
                this.coverURL == null && state.language == null && this.language == null &&
                state.subjects == null && this.subjects == null && state.listingsInfo == null &&
                this.listingsInfo == null){
            return true;
        }

        if (state.title == null || this.title == null || state.author == null ||
                this.author == null || state.year == 0 || this.year == 0 ||
                state.ISBN == null || this.ISBN == null || state.coverURL == null ||
                this.coverURL == null || state.language == null || this.language == null ||
                state.subjects == null || this.subjects == null || state.listingsInfo == null ||
                this.listingsInfo == null) {
            return false;
        }

        return (state.title.equals(this.title) && state.author.equals(this.author)&&
                state.year == this.year && state.ISBN.equals(this.ISBN) &&
                state.coverURL.equals(this.coverURL) && state.language.equals(this.language) &&
                state.subjects.equals(this.subjects) && state.listingsInfo.equals(this.listingsInfo));
    }

}
