package data_access;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    /** Returns whether the User exists in our map of User accounts for a given username
     * @param identifier the user's username
     * @return a boolean corresponding to whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /** Add a given User and their username to our map of User accounts
     * @param user the data to save
     */
    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Returns the User corresponding to the given username
     * @param username username of the User we want to retrieve
     * @return The User corresponding to the given username
     */
    @Override
    public User get(String username) {
        for (User user: users.values()){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
