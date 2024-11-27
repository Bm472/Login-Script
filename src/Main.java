import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<String, User> users;
    private static Scanner scanner;

    public static void main(String[] args) {

        //Initialise variables
        users = new HashMap<>();
        scanner = new Scanner(System.in);
        boolean exit = false;
        int value = -1;

        //Loop until user presses exit key
        do {

            //Display message and call appropriate method depending on user input
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

    //Handle the login message display and logic
    public static String login() {

        //Initialise variables
        boolean success = false;
        String loggedInUsername = "";
        String loggedInFirstName = "";

        //Loop until successfully logged in
        while (!success) {

            System.out.println("Enter your username: ");
            String username = scanner.next();

            //Check to see if username exists - alert user if not
            if (!users.containsKey(username)) {

                System.out.println("Username does not exist");

            } else {

                System.out.println("Enter your password: ");
                String password = scanner.next();

                //Obtain user object to compare passwords
                User user = users.get(username);

                //Check to see if passwords match - log in if true, alert user if not
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

        //Loop until provided username doesn't exist
        while(users.containsKey(username)) {
            System.out.println("Username already exists, please choose another username: ");
            username = scanner.next();
        }

        //Gather information from user
        System.out.println("Enter your password: ");
        String password = scanner.next();

        System.out.println("Enter your first name: ");
        String firstName = scanner.next();

        System.out.println("Enter your last name: ");
        String lastName = scanner.next();

        System.out.println("Enter your age: ");
        byte age = scanner.nextByte();

        //Create new user object with provided information and add to Map
        User newUser = new User(username, password, firstName, lastName, age);
        users.put(username, newUser);
        System.out.println("User created successfully");
    }

    public static void deleteUser() {

        String username = "";

        //Loop until username that exists is provided
        do {
            System.out.println("Enter your username: ");
            username = scanner.next();
        } while(!users.containsKey(username));

        //Obtain User object from username
        User deletedUser = users.get(username);
        String password = "";

        //Loop until correct password is provided
        do {
            System.out.println("Enter your password: ");
            password = scanner.next();
        }  while (!User.hashPassword(password).equals(deletedUser.getPassword()));

        //Remove User object from Map
        users.remove(username);
        System.out.println("User deleted successfully");

    }
}