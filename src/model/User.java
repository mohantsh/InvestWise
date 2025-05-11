package model;

import java.io.Serializable;

/**
 * Represents a user in the investment system.
 * This class implements Serializable to allow object persistence.
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    /**
     * Creates a new user with the specified username and password.
     *
     * @param username the username for the new user
     * @param password the password for the new user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the user's ID.
     *
     * @return the user's ID
     */
    public int getId() { return id; }

    /**
     * Sets the user's ID.
     *
     * @param id the ID to set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the user's username.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() { return password; }
}
