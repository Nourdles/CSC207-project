package use_case.searchfilter;
import entity.Book;
import java.util.ArrayList;

public interface SearchFilterInputBoundary {

    void applyFilters(SearchFilterInputData searchFilterInputData);
}
