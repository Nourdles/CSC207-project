package data_access;

import entity.CommonUser;
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

    private UserFactory userFactory;

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
                assert header.equals("username,password,creation_time, email, phoneNumber, city");

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

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }

    public void clear(){
        accounts.clear();
        this.save();
    }

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
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public List<Listing> getBookListings(String ISBN) {
        return null;
    }

    public CommonUser findUserByUsername(String username) {
        User user = accounts.get(username);
        if (user instanceof CommonUser) {
            return (CommonUser) user;
        } else {
            return null;
        }
    }

    @Override
    public String findCity(String username) {
        User user = accounts.get(username);
        return (user != null) ? user.getCity() : null;
    }

    @Override
    public String findEmail(String username) {
        User user = accounts.get(username);
        return (user != null) ? user.getEmail() : null;
    }

    @Override
    public String findPhoneNumber(String username) {
        User user = accounts.get(username);
        return (user != null) ? user.getPhoneNumber() : null;
    }

    public boolean passwordMeetsReq(String password) {
        if (password.length() < 6){
            return false;
        }
        else {
            boolean lowerCase = false;
            boolean upperCase = false;
            boolean number = false;
            for (char c: password.toCharArray()){
                if (c == ' '){
                    return false;
                } else if (Character.isLowerCase(c)) {
                    lowerCase = true;
                } else if (Character.isUpperCase(c)) {
                    upperCase = true;
                } else if (Character.isDigit(c)) {
                    number = true;
                }
            }
            return (lowerCase && upperCase && number);
        }
    }

    public boolean emailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();

    }

    public void updatePhoneNumber(String username, String newPhoneNumber) {
        User user = accounts.get(username);
        if (user != null) {
            user.setPhoneNumber(newPhoneNumber);
            save();
        }
    }

    public void updateCity(String username, String newCity) {
        User user = accounts.get(username);
        if (user != null) {
            user.setCity(newCity);
            save();
        }
    }

    public void updateEmail(String username, String newEmail) {
        User user = accounts.get(username);
        if (user != null) {
            user.setEmail(newEmail);
            save();
        }
    }

    public void updatePassword(String username, String newPassword) {
        User user = accounts.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            save();
        }
    }
}
