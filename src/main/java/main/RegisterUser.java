package main;

import dao.UserDAO;
import models.User;
import java.util.Scanner;

public class RegisterUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (UserDAO.isUserExists(email)) {
            System.out.println("User with this email already exists!");
        } else {
            User user = new User(username, email, password);
            boolean success = UserDAO.registerUser(user);

            if (success) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Registration failed!");
            }
        }
    }
}
