import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the persistence of user data using Java serialization.
 * This class provides methods for registering new users and authenticating existing ones.
 */
public class UserStorage {
    private static final String FILENAME = "db/users.ser";
    private static List<User> users = new ArrayList<>();
    private static int nextId = 1;

    static {
        loadUsers();
    }

    /**
     * Saves the current list of users to the serialized file.
     * The file is stored in the db directory.
     */
    public static void saveUsers() {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of users from the serialized file.
     * If the file doesn't exist, an empty list is created.
     * The nextId is set to one more than the highest existing user ID.
     */
    @SuppressWarnings("unchecked")
    private static void loadUsers() {
        File file = new File(FILENAME);
        if (!file.exists()) return;

        try (FileInputStream fis = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            users = (List<User>) ois.readObject();
            // Find the highest ID to set nextId
            nextId = users.stream()
                    .mapToInt(User::getId)
                    .max()
                    .orElse(0) + 1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registers a new user in the system.
     *
     * @param user the user to register
     * @return true if registration was successful, false if username already exists
     */
    public static boolean register(User user) {
        // Check if username already exists
        if (users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
            return false;
        }
        
        user.setId(nextId++);
        users.add(user);
        saveUsers();
        return true;
    }

    /**
     * Authenticates a user and returns their ID.
     *
     * @param username the username to check
     * @param password the password to check
     * @return the user's ID if authentication is successful, -1 otherwise
     */
    public static int loginAndGetId(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .mapToInt(User::getId)
                .findFirst()
                .orElse(-1);
    }
} 