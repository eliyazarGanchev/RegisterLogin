package main;

import dao.UserDAO;
import java.util.Scanner;

public class LoginUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = UserDAO.loginUser(email, password);

        if (isAuthenticated) {
            System.out.println("Login successful! Welcome back!");
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }

        scanner.close();
    }
}
