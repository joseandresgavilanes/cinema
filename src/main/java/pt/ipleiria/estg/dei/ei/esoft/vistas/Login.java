package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.SessionManager;
import pt.ipleiria.estg.dei.ei.esoft.models.User;
import pt.ipleiria.estg.dei.ei.esoft.models.UserRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JTextField userTextField;
    private JButton loginButton;
    private JLabel imgUser;
    private JLabel imgPassword;
    private JButton registerButton;
    private JPasswordField passwordField;
    private JLabel loginText;

    // 🔁 Callback a ejecutar después del login
    private Runnable onLoginSuccess;

    // 🔧 Constructor sin callback (por defecto)
    public Login() {
        this(null);
    }

    // 🔧 Constructor con callback
    public Login(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;

        setTitle("Login");
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.setBackground(Color.DARK_GRAY);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });

        registerButton.addActionListener(e -> {
            RegisterForm registerForm = new RegisterForm(() -> {
                // Lógica post-registro (opcional)
            });
            registerForm.setVisible(true);
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void doLogin() {
        String username = userTextField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Username and Password required.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Optional<User> opt = DataStore.getInstance()
                .getUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username)
                        && u.getPassword().equals(password))
                .findFirst();

        if (!opt.isPresent()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Incorrect credentials.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        User user = opt.get();
        SessionManager.setCurrentUser(user);

        // ✅ Ejecutar acción post-login si existe
        if (onLoginSuccess != null) {
            onLoginSuccess.run();
        }

        // Abrir panel de administrador si corresponde
        if (user.getRole() == UserRole.ADMIN) {
            AdminHomepage adminHome = new AdminHomepage();
            adminHome.setVisible(true);
        }

        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
