package use_case.book_info;

import entity.Listing;
import entity.User;
import java.util.List;

public interface BookInfoDataAccessInterface {
    List<Listing> getBookListings(String ISBN);
    String findCity(String username);
    String findEmail(String username);
    String findPhoneNumber(String username);
    void save(User user);
}
