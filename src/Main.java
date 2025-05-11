import java.util.Scanner;
import logic.ComplianceChecker;
import model.Asset;
import model.User;
import storage.AssetStorage;
import storage.UserStorage;

/**
 * The main application class for the Investment Console Application.
 * This class provides the user interface and coordinates between different components
 * of the system, including user management, asset management, and compliance checking.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * The main entry point of the application.
     * Displays the main menu and handles user input for navigation.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Invest System ===");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> signUp();
                case 2 -> login();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Handles the user registration process.
     * Prompts for username and password, then attempts to register the user.
     */
    private static void signUp() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);
        boolean success = UserStorage.register(user);
        if (success) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists or error occurred.");
        }
    }

    /**
     * Handles the user login process.
     * Prompts for username and password, then attempts to authenticate the user.
     * If successful, shows the asset management menu.
     */
    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        int userId = UserStorage.loginAndGetId(username, password);
        if (userId != -1) {
            System.out.println("Login successful. Welcome, " + username + "!");
            showAssetMenu(userId);
        } else {
            System.out.println("Login failed. Try again.");
        }
    }

    /**
     * Displays and handles the asset management menu for a logged-in user.
     * Provides options to view, add, edit, delete assets, check compliance,
     * and link bank accounts.
     *
     * @param userId the ID of the logged-in user
     */
    private static void showAssetMenu(int userId) {
        while (true) {
            System.out.println(" === Asset Menu ===");
            System.out.println("1. View Assets");
            System.out.println("2. Add Asset");
            System.out.println("3. Edit Asset");
            System.out.println("4. Delete Asset");
            System.out.println("5. Compliance Check");
            System.out.println("6. Link Bank Account");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    var assets = AssetStorage.getAssetsByUser(userId);
                    if (assets.isEmpty()) System.out.println("No assets.");
                    else for (Asset a : assets) System.out.println(a);
                }
                case 2 -> {
                    System.out.print("Enter asset type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter asset value: ");
                    double value = Double.parseDouble(scanner.nextLine());
                    AssetStorage.addAsset(new Asset(userId, type, value));
                    System.out.println("Asset added.");
                }
                case 3 -> {
                    System.out.print("Enter asset ID to edit: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("New type: ");
                    String type = scanner.nextLine();
                    System.out.print("New value: ");
                    double value = Double.parseDouble(scanner.nextLine());
                    AssetStorage.updateAsset(new Asset(id, userId, type, value));
                    System.out.println("Asset updated.");
                }
                case 4 -> {
                    System.out.print("Enter asset ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    AssetStorage.deleteAsset(id);
                    System.out.println("Asset deleted.");
                }
                case 5 -> {
                    var assets = AssetStorage.getAssetsByUser(userId);
                    var report = ComplianceChecker.checkCompliance(assets);
                    System.out.println(" === Compliance Report ===");
                    for (String line : report) System.out.println(line);
                }
                case 6 -> {
                    System.out.print("Enter card number: ");
                    scanner.nextLine(); // ignore input
                    System.out.print("Enter CVV: ");
                    scanner.nextLine(); // ignore input
                    System.out.print("Enter OTP (123456): ");
                    String otp = scanner.nextLine();
                    if (otp.equals("123456")) {
                        System.out.println("Bank account linked successfully.");
                    } else {
                        System.out.println("Bank linking failed: Invalid OTP.");
                    }
                }
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
