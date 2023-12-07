package use_case.view_listings;

public class ListingsInputData {
    final private String username;

    /**
     * Create a new Listings Input Data with the given parameter
     * @param username String that represents the username of the currently logged-in User
     */
    public ListingsInputData(String username){this.username = username;}

    /**
     * Returns the username of the currently logged-in User in this
     * @return String that represents the username of the currently logged-in User
     */
    public String getUsername(){return username;}
}