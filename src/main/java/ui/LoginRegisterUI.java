package ui;

import dao.UserDAO;
import models.User;
import utils.RegisterUtils;
import utils.RegisterUtils;

import javax.swing.*;
import java.awt.*;

public class LoginRegisterUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField regUsernameField, regEmailField, loginEmailField;
    private JPasswordField regPasswordField, loginPasswordField;

    public LoginRegisterUI() {
        setTitle("User Authentication");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        UIManager.put("Label.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("PasswordField.font", new Font("Arial", Font.PLAIN, 14));

        JPanel registerPanel = createRegisterPanel();
        JPanel loginPanel = createLoginPanel();

        mainPanel.add(registerPanel, "Register");
        mainPanel.add(loginPanel, "Login");

        add(mainPanel);
        cardLayout.show(mainPanel, "Register");

        setVisible(true);
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Register", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel userLabel = new JLabel("Username:");
        regUsernameField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        regEmailField = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        regPasswordField = new JPasswordField(15);

        JButton registerButton = createStyledButton("Register");
        JButton switchToLogin = createStyledButton("Go to Login");

        registerButton.addActionListener(e -> registerUser());
        switchToLogin.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
        gbc.gridy++;
        panel.add(userLabel, gbc);
        gbc.gridy++;
        panel.add(regUsernameField, gbc);
        gbc.gridy++;
        panel.add(emailLabel, gbc);
        gbc.gridy++;
        panel.add(regEmailField, gbc);
        gbc.gridy++;
        panel.add(passLabel, gbc);
        gbc.gridy++;
        panel.add(regPasswordField, gbc);
        gbc.gridy++;
        panel.add(registerButton, gbc);
        gbc.gridy++;
        panel.add(switchToLogin, gbc);

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel emailLabel = new JLabel("Email:");
        loginEmailField = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        loginPasswordField = new JPasswordField(15);

        JButton loginButton = createStyledButton("Login");
        JButton switchToRegister = createStyledButton("Go to Register");

        loginButton.addActionListener(e -> loginUser());
        switchToRegister.addActionListener(e -> cardLayout.show(mainPanel, "Register"));

        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
        gbc.gridy++;
        panel.add(emailLabel, gbc);
        gbc.gridy++;
        panel.add(loginEmailField, gbc);
        gbc.gridy++;
        panel.add(passLabel, gbc);
        gbc.gridy++;
        panel.add(loginPasswordField, gbc);
        gbc.gridy++;
        panel.add(loginButton, gbc);
        gbc.gridy++;
        panel.add(switchToRegister, gbc);

        return panel;
    }

    private void registerUser() {
        String username = regUsernameField.getText();
        String email = regEmailField.getText();
        String password = new String(regPasswordField.getPassword());

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        if (!RegisterUtils.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format! Email must contain '@' and a valid domain.");
            return;
        }

        if (!RegisterUtils.isValidPassword(password)) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters, " +
                    "contain one uppercase, one lowercase, and one number.");
            return;
        }

        if (UserDAO.isUserExists(email)) {
            JOptionPane.showMessageDialog(this, "User already exists!");
            return;
        }

        User user = new User(username, email, password);
        boolean success = UserDAO.registerUser(user);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed!");
        }
    }


    private void loginUser() {
        String email = loginEmailField.getText();
        String password = new String(loginPasswordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        boolean success = UserDAO.loginUser(email, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password!");
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(72, 133, 237));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public static void main(String[] args) {
        new LoginRegisterUI();
    }
}
