/**
 * Write a description of class UltraSimpleUserStorage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*; // For File I/O and Serialization
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a demo for using Java serialization
 * @see ...... link 
 */
public class UltraSimpleUserStorage {

    /** Define the User class. It MUST implement Serializable.
      * Making it a static nested class allows it to be in the same .java file.
      *
    */
    static class User implements Serializable {
        
        int id; // Using package-private for extreme simplicity, normally private with getters
        String name;
        String email;

        /**
         * This is a constructor for initiating use id, name and email
         * @param id is an integer value for user id
         * @param name is a string representing user name
         * @param email is a string representing user email
         * @return This is a constructor having NO return value
         */
        public User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        /**
         * toString converts object content to string
         * @return a string representation of the current user
         */
        @Override
        public String toString() {
            // Simple string representation for printing
            return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
        }
    }

    // The filename where the user list will be stored.
    private static final String FILENAME = "users_list.ser"; // .ser is a common extension

    public static void main(String[] args) throws Exception {
        // By adding "throws Exception" to main, we don't need explicit try-catch
        // blocks for IOException, ClassNotFoundException, etc., for this simple demo.
        // In real applications, more specific error handling is usually preferred.

        // --- PART 1: CREATE AND SAVE USERS ---
        List<User> usersToSave = new ArrayList<>();
        usersToSave.add(new User(1, "Alice Smith", "alice@example.com"));
        usersToSave.add(new User(2, "Bob Johnson", "bob@example.com"));
        usersToSave.add(new User(3, "Charlie Brown", "charlie@example.com"));

        System.out.println("Saving users to file: " + FILENAME);
        // Use try-with-resources to ensure streams are closed automatically
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
           
            // Serialize the entire list object to the file
            oos.writeObject(usersToSave);
        }
        System.out.println("Users saved successfully.");


        // --- PART 2: LOAD USERS FROM FILE ---
        // In a real application, loading might happen in a separate run or different part of the code.
        // For this demo, we'll load them back immediately.

        System.out.println("\nLoading users from file: " + FILENAME);
        List<User> loadedUsers;
        try (FileInputStream fis = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
           
            // Deserialize the object from the file.
            // This reads the entire list back in one go.
            // A cast is necessary because readObject() returns an Object.
            // This can throw ClassNotFoundException if the User class definition is missing
            // or ClassCastException if the file doesn't contain a List<User>.
            loadedUsers = (List<User>) ois.readObject();
        }
       
        System.out.println("Users loaded successfully:");
        // Iterate and print the loaded users to verify
        for (User user : loadedUsers) {
            System.out.println(user);
        }
    }
}
