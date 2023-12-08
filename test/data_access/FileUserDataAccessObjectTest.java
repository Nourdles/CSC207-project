package data_access;

import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileUserDataAccessObjectTest {
    private CommonUserFactory userFactory;
    private String username;
    private String password;
    private LocalDateTime ldt;
    private String email;
    private String phoneNumber;
    private String city;
    private User user;
    private FileUserDataAccessObject fileUserDAO;
    private Map<String, User> accounts = new HashMap<>();
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        userFactory = new CommonUserFactory();
        username = "Tommy";
        password = "Abcd1234!";
        ldt = LocalDateTime.MAX;
        email = "tommy123@gmail.com";
        phoneNumber = "1234554321";
        city = "New York";
        user = userFactory.create(username, password, ldt, email, phoneNumber, city);
        fileUserDAO = new FileUserDataAccessObject("users.csv", userFactory);
    }
    @org.junit.jupiter.api.Test
    void save() {
        accounts = fileUserDAO.getAccounts();
        Map<String, User> expectedAccounts = new HashMap<>(accounts);
        expectedAccounts.put(username, user);
        fileUserDAO.save(user);
        assertEquals(expectedAccounts.size(), accounts.size());
        assertEquals(expectedAccounts.keySet(), accounts.keySet());
        for(User user: expectedAccounts.values()){
            assertTrue(accounts.containsValue(user));
        }
    }

    @Test
    void delete() {
        fileUserDAO.save(user);
        accounts = fileUserDAO.getAccounts();
        Map<String, User> expectedAccounts = new HashMap<>(accounts);
        assertTrue(accounts.containsKey(username));
        assertTrue(accounts.containsValue(user));
        fileUserDAO.delete(username);
        expectedAccounts.remove(username);
        assertEquals(accounts.keySet(), expectedAccounts.keySet());
        assertEquals(expectedAccounts.keySet(), accounts.keySet());
        for(User savedUser: expectedAccounts.values()){
            assertFalse(accounts.containsValue(user));
        }
    }

    @Test
    void makeUsersCSV() throws IOException {
        String testUserString = "usersTest.csv";
        Path testUserPath = Paths.get(testUserString);
        if (Files.exists(testUserPath)){
            Files.delete(testUserPath);
        }
        File file = new File(testUserString);
        assertEquals(file.length(),0);
        FileUserDataAccessObject newfileUserDAO = new FileUserDataAccessObject(testUserString, userFactory);
        Path usersTestPath = Paths.get("usersTest.csv");
        assertTrue(Files.exists(usersTestPath));
    }

    @Test
    void get(){
        fileUserDAO.save(user);
        assertEquals(fileUserDAO.get(username), user);
    }
    @Test
    void successExistsByName(){
        fileUserDAO.save(user);
        assertTrue(fileUserDAO.existsByName(username));
    }
    @Test
    void failExistsByName(){
        fileUserDAO.save(user);
        assertFalse(fileUserDAO.existsByName(" ."));
    }
    @Test
    void getBookListings(){
    }

    @Test
    void findCity() {
    }

    @Test
    void findEmail() {
    }

    @Test
    void findPhoneNumber() {
    }

    @Test
    void updatePhoneNumber() {
        String newPhoneNumber = "0001112222";
        fileUserDAO.save(user);
        fileUserDAO.updatePhoneNumber(username, newPhoneNumber);
        assertEquals(user.getPhoneNumber(), newPhoneNumber);
    }

    @Test
    void updateCity() {
        String newCity = "England";
        fileUserDAO.save(user);
        fileUserDAO.updateCity(username, newCity);
        assertEquals(user.getCity(), newCity);
    }

    @Test
    void updateEmail() {
        String newEmail = "tommy234@gmail.com";
        fileUserDAO.save(user);
        fileUserDAO.updateEmail(username, newEmail);
        assertEquals(user.getEmail(), newEmail);
    }

    @Test
    void updatePassword() {
        String newPassword = "Qwerty123$";
        fileUserDAO.save(user);
        fileUserDAO.updatePassword(username, newPassword);
        assertEquals(user.getPassword(), newPassword);
    }
}