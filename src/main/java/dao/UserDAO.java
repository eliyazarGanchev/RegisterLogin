package dao;

import models.User;
import utils.DatabaseConnection;
import utils.RegisterUtils;
import utils.RegisterUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean registerUser(User user) {
        if (!RegisterUtils.isValidEmail(user.getEmail())) {
            System.out.println("Invalid email format!");
            return false;
        }

        if (!RegisterUtils.isValidPassword(user.getPassword())) {
            System.out.println("Password does not meet security requirements!");
            return false;
        }

        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, RegisterUtils.hashPassword(user.getPassword())); // Hash password

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean isUserExists(String email) {
        String querySelect = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySelect)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loginUser(String email, String password) {
        String queryPass = "SELECT password FROM users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(queryPass)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                return RegisterUtils.checkPassword(password, storedHashedPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
