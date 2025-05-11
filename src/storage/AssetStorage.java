package storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Asset;

/**
 * Manages the persistence of asset data using Java serialization.
 * This class provides methods for managing investment assets, including adding,
 * updating, deleting, and retrieving assets for specific users.
 */
public class AssetStorage {
    private static final String FILENAME = "db/assets.ser";
    private static List<Asset> assets = new ArrayList<>();
    private static int nextId = 1;

    static {
        loadAssets();
    }

    /**
     * Saves the current list of assets to the serialized file.
     * The file is stored in the db directory.
     */
    public static void saveAssets() {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(assets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of assets from the serialized file.
     * If the file doesn't exist, an empty list is created.
     * The nextId is set to one more than the highest existing asset ID.
     */
    @SuppressWarnings("unchecked")
    private static void loadAssets() {
        File file = new File(FILENAME);
        if (!file.exists()) return;

        try (FileInputStream fis = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            assets = (List<Asset>) ois.readObject();
            nextId = assets.stream()
                    .mapToInt(Asset::getId)
                    .max()
                    .orElse(0) + 1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new asset to the system.
     * The asset is assigned a unique ID and saved to the file.
     *
     * @param asset the asset to add
     */
    public static void addAsset(Asset asset) {
        asset.setId(nextId++);
        assets.add(asset);
        saveAssets();
    }

    /**
     * Retrieves all assets belonging to a specific user.
     *
     * @param userId the ID of the user whose assets to retrieve
     * @return a list of assets owned by the specified user
     */
    public static List<Asset> getAssetsByUser(int userId) {
        return assets.stream()
                .filter(a -> a.getUserId() == userId)
                .toList();
    }

    /**
     * Updates an existing asset's information.
     * Only the type and value can be updated.
     *
     * @param asset the asset with updated information
     */
    public static void updateAsset(Asset asset) {
        assets.stream()
                .filter(a -> a.getId() == asset.getId())
                .findFirst()
                .ifPresent(a -> {
                    a.setType(asset.getType());
                    a.setValue(asset.getValue());
                    saveAssets();
                });
    }

    /**
     * Deletes an asset from the system.
     *
     * @param assetId the ID of the asset to delete
     */
    public static void deleteAsset(int assetId) {
        assets.removeIf(a -> a.getId() == assetId);
        saveAssets();
    }
} 