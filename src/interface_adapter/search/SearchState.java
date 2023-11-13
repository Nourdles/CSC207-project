package src.interface_adapter.search;

public class SearchState {
    private String searchField;
    private String searchFieldError = null;

    public SearchState(SearchState copy) {
        searchField = copy.searchField;
        searchFieldError = copy.searchFieldError;
    }

    public SearchState() {

    }

    public String getSearchField() {
        return searchField;
    }

    public String getSearchFieldError() {
        return searchFieldError;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public void setSearchFieldError(String searchFieldError) {
        this.searchFieldError = searchFieldError;
    }
}
