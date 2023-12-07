package data_access;

import entity.Listing;
import entity.User;
import entity.UserFactory;
import use_case.book_info.BookInfoDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, BookInfoDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private final UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);
        headers.put("email", 3);
        headers.put("phoneNumber", 4);
        headers.put("city", 5);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time,email,phoneNumber,city");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String email = String.valueOf(col[headers.get("email")]);
                    String phoneNumber = String.valueOf(col[headers.get("phoneNumber")]);
                    String city = String.valueOf(col[headers.get("city")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = userFactory.create(username, password, ldt, email, phoneNumber, city);
                    accounts.put(username, user);
                }
            } catch (IOException e){
                System.out.println("There was a problem reading the file.");
            }
        }
    }

    /**
     * Save a new User to our existing hash map of User accounts
     * @param user the User to save
     */
    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }

    /**
     * Deletes the User with the given username if they exist.
     * @param username
     */
    public void delete(String username){
        for(String id : accounts.keySet()){
            if(id.equals(username)){
                accounts.remove(username);
            }
        }
        this.save();
    }

    /**
     * Returns the User that has the specified username
     * @param username username of the User we want to find
     * @return the User that has the specified username
     */
    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s",
                        user.getUsername(), user.getPassword(),
                        user.getCreationTime(), user.getEmail(),
                        user.getPhoneNumber(), user.getCity());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return a boolean representing whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    /**
     * Returns a list of listings for a specific User, given that User's username
     * @param username Username of the User we want to fetch the listings of
     * @return a list of listings for a specific User, given that User's username
     */
    @Override
    public List<Listing> getBookListings(String username) {
        return null;
    }

    /**
     * Return the city of a specific User given their username
     * @param username username of the User whose city we want
     * @return a string representing the city of a specific User given their username
     */
    @Override
    public String findCity(String username) {
        User user = accounts.get(username);
        return (user != null) ? user.getCity() : null;
    }

    /**
     * Return the email of a specific User given their username
     * @param username username of the User whose email we want
     * @return a string representing the email of a specific User given their username
     */
    @Override
    public String findEmail(String username) {
        User user = accounts.get(username);
        return (user != null) ? user.getEmail() : null;
    }

    /**
     * Return the phone number of a specific User given their username
     * @param username username of the User whose phone number we want
     * @return a string representing the phone number of a specific User given their username
     */
    @Override
    public String findPhoneNumber(String username) {
        User user = accounts.get(username);
        return (user != null) ? user.getPhoneNumber() : null;
    }

    /**
     * Overwrites the phone number of a given User with a new password
     * @param username
     * @param newPhoneNumber
     */
    public void updatePhoneNumber(String username, String newPhoneNumber) {
        User user = accounts.get(username);
        if (user != null) {
            user.setPhoneNumber(newPhoneNumber);
            save();
        }
    }

    /**
     * Overwrites the city of a given User with a new password
     * @param username username of the User whose city we want to change
     * @param newCity String we want to replace the current city with
     */
    public void updateCity(String username, String newCity) {
        User user = accounts.get(username);
        if (user != null) {
            user.setCity(newCity);
            save();
        }
    }

    /**
     * Overwrites the email of a given User with a new password
     * @param username username of the User whose email we want to change
     * @param newEmail String we want to replace the current email with
     */
    public void updateEmail(String username, String newEmail) {
        User user = accounts.get(username);
        if (user != null) {
            user.setEmail(newEmail);
            save();
        }
    }

    /**
     * Overwrites the password of a given User with a new password
     * @param username username of the User whose password we want to change
     * @param newPassword String we want to replace the current password with
     */
    public void updatePassword(String username, String newPassword) {
        User user = accounts.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            save();
        }
    }
    public Map<String, User> getAccounts(){return accounts;}
}
