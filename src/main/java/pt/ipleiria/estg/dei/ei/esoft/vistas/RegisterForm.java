package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.User;
import pt.ipleiria.estg.dei.ei.esoft.models.UserRole;

import java.awt.*;

public class RegisterForm extends javax.swing.JFrame {
    private JPanel userPanel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JTextField emailTextField;
    private JTextField documentTextField;
    private JButton saveButton;

    private final Runnable onUserCreated;

    public RegisterForm(Runnable onUserCreated) {
        this.onUserCreated = onUserCreated;
        userPanel.setBackground(Color.DARK_GRAY);

        setTitle("Create User");
        setContentPane(userPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        saveButton.addActionListener(e -> {
            String username = usernameTextField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String email    = emailTextField.getText().trim();
            String document = documentTextField.getText().trim();

            // Validación básica
            if (username.isEmpty() || password.isEmpty() ||
                    email.isEmpty()    || document.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "All fields are required.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Crear User y asignar rol
            User user = new User(username, password, email, document);
            user.setRole(UserRole.USER);

            // Persistir
            DataStore.getInstance().addUser(user);

            JOptionPane.showMessageDialog(
                    this,
                    "User created.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
            dispose();

            if (onUserCreated != null) {
                onUserCreated.run();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterForm(null).setVisible(true));
    }
}