import java.io.Serializable;

/**
 * Represents an investment asset in the system.
 * This class implements Serializable to allow object persistence.
 */
public class Asset implements Serializable {
    private int id;
    private int userId;
    private String type;
    private double value;

    /**
     * Creates a new asset for a user.
     *
     * @param userId the ID of the user who owns this asset
     * @param type the type of asset (e.g., "Stock", "Bond", "Real Estate")
     * @param value the monetary value of the asset
     */
    public Asset(int userId, String type, double value) {
        this.userId = userId;
        this.type = type;
        this.value = value;
    }

    /**
     * Creates a new asset with all fields specified.
     *
     * @param id the unique identifier for the asset
     * @param userId the ID of the user who owns this asset
     * @param type the type of asset
     * @param value the monetary value of the asset
     */
    public Asset(int id, int userId, String type, double value) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.value = value;
    }

    /**
     * Gets the asset's ID.
     *
     * @return the asset's ID
     */
    public int getId() { return id; }

    /**
     * Sets the asset's ID.
     *
     * @param id the ID to set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the ID of the user who owns this asset.
     *
     * @return the user's ID
     */
    public int getUserId() { return userId; }

    /**
     * Gets the type of asset.
     *
     * @return the asset type
     */
    public String getType() { return type; }

    /**
     * Gets the monetary value of the asset.
     *
     * @return the asset value
     */
    public double getValue() { return value; }

    /**
     * Sets the type of asset.
     *
     * @param type the new asset type
     */
    public void setType(String type) { this.type = type; }

    /**
     * Sets the monetary value of the asset.
     *
     * @param value the new asset value
     */
    public void setValue(double value) { this.value = value; }

    /**
     * Returns a string representation of the asset.
     *
     * @return a string containing the asset's ID, type, and value
     */
    @Override
    public String toString() {
        return "Asset ID: " + id + " | Type: " + type + " | Value: " + value;
    }
} 