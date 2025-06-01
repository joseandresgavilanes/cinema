package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Session;
import pt.ipleiria.estg.dei.ei.esoft.models.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ShowSession extends JFrame {
    private JPanel SessionPanel;
    private JPanel MovieContainer;
    private JScrollPane ScrollPanel;
    private JButton billboardButton;
    private JButton barButton;
    private JButton loginButton;
    private JLabel image;
    private JPanel Navbar;
    private JButton ticketsButton;
    private JButton receiptsButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShowSession().setVisible(true));
    }

    public ShowSession() {
        // General setup
        setTitle("LumiCine");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main layout
        SessionPanel = new JPanel(new BorderLayout());
        SessionPanel.setBackground(Color.BLACK);
        setContentPane(SessionPanel);

        // Navbar
        Navbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        Navbar.setBackground(Color.DARK_GRAY);
        SessionPanel.add(Navbar, BorderLayout.NORTH);

        // Logo
        image = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImage = icon.getImage().getScaledInstance(180, 90, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(scaledImage));
        Navbar.add(image);

        // Botones navbar
        billboardButton = createButton("Billboard");
        barButton = createButton("Snacks");
        loginButton = createButton("Login");

        Navbar.add(billboardButton);
        Navbar.add(barButton);
        Navbar.add(loginButton);

        // Contenedor de películas
        MovieContainer = new JPanel();
        MovieContainer.setLayout(new BoxLayout(MovieContainer, BoxLayout.Y_AXIS));
        MovieContainer.setBackground(Color.BLACK);

        ScrollPanel = new JScrollPane(MovieContainer);
        ScrollPanel.setBorder(null);
        ScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
        SessionPanel.add(ScrollPanel, BorderLayout.CENTER);

        // Mostrar películas
        List<Session> funciones = DataStore.getInstance().getSessions();
        for (Session f : funciones) {
            ShowMoviePanel panel = new ShowMoviePanel(f);
            panel.setTitulo(f.getMovie().getTitle());
            panel.setDescripcion(f.getMovie().getDescription());
            panel.setPhoto(f.getMovie().getImagePath());
            panel.setBackground(new Color(30, 30, 30));
            panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            MovieContainer.add(panel);
        }

        // Botón login
        loginButton.addActionListener(e -> {
            Login loginWindow = new Login();
            loginWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            loginWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent we) {
                    if (SessionManager.isLoggedIn()) {
                        setupPostLogin();
                    }
                }
            });
            loginWindow.setVisible(true);
        });

        if (SessionManager.isLoggedIn()) {
            setupPostLogin();
        }

        // Botón bar
        barButton.addActionListener(e -> {
            ShowProduct showWindow = new ShowProduct();
            showWindow.setVisible(true);
        });
    }

    private void setupPostLogin() {
        loginButton.setVisible(false);

        if (ticketsButton == null) {
            ticketsButton = createButton("My Tickets");
            receiptsButton = createButton("My Receipts");

            ticketsButton.addActionListener(e -> {
                ListTickets ticketsWindow = new ListTickets();
                ticketsWindow.setVisible(true);
            });

            // Future implementation
//            receiptsButton.addActionListener(e -> {
//                ShowReceipts receiptsWindow = new ShowReceipts();
//                receiptsWindow.setVisible(true);
//            });

            Navbar.add(ticketsButton);
            Navbar.add(receiptsButton);
            Navbar.revalidate();
            Navbar.repaint();
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(229, 9, 20)); // Netflix red
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }
}
