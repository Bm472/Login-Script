import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<String, User> users;
    private static Scanner scanner;

    public static void main(String[] args) {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
        boolean exit = false;
        int value = -1;

        do {
            System.out.println("""
                    Menu:
                    1. Log In
                    2. Create User
                    3. Delete User
                    9. Exit""");
            value = scanner.nextInt();

            switch (value) {
                case 1:
                    login();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 9:
                    exit = true;
                    break;

            }

        } while (!exit);

        System.out.println("Goodbye!");
    }

    public static String login() {

        boolean success = false;
        String loggedInUsername = "";
        String loggedInFirstName = "";

        while (!success) {
            System.out.println("Enter your username: ");
            String username = scanner.next();
            if (!users.containsKey(username)) {
                System.out.println("Username does not exist");
            } else {
                System.out.println("Enter your password: ");
                String password = scanner.next();
                User user = users.get(username);
                if (User.hashPassword(password).equals(user.getPassword())) {
                    success = true;
                    loggedInUsername = username;
                    loggedInFirstName = user.getFirstName();
                } else {
                    System.out.println("Incorrect password");
                }
            }
        }

        System.out.println("Welcome, " + loggedInFirstName + "!");

        return loggedInUsername;

    }

    public static void createUser() {
        System.out.println("Enter your username: ");
        String username = scanner.next();

        while(users.containsKey(username)) {
            System.out.println("Username already exists, please choose another username: ");
            username = scanner.next();
        }

        System.out.println("Enter your password: ");
        String password = scanner.next();

        System.out.println("Enter your first name: ");
        String firstName = scanner.next();

        System.out.println("Enter your last name: ");
        String lastName = scanner.next();

        System.out.println("Enter your age: ");
        byte age = scanner.nextByte();

        User newUser = new User(username, password, firstName, lastName, age);
        users.put(username, newUser);
        System.out.println("User created successfully");
    }

    public static void deleteUser() {

        String username = "";
        do {
            System.out.println("Enter your username: ");
            username = scanner.next();
        } while(!users.containsKey(username));

        User deletedUser = users.get(username);

        String password = "";
        do {
            System.out.println("Enter your password: ");
            password = scanner.next();
        }  while (!User.hashPassword(password).equals(deletedUser.getPassword()));

        users.remove(username);
        System.out.println("User deleted successfully");





    }
}