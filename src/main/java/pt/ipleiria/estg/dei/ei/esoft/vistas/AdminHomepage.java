package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;

public class AdminHomepage extends JFrame {
    private JButton manageSessionsButton;
    private JButton manageMoviesButton;
    private JButton statisticsButton;
    private JButton viewReceiptsButton;
    private JButton manageRoomsButton;
    private JButton manageBarButton;
    private JLabel image;
    private JPanel admin;

    public AdminHomepage() {
        setContentPane(admin);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImage = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(scaledImage));

        manageMoviesButton.addActionListener(e -> {
            MoviesAdminPanel moviesPanel = new MoviesAdminPanel();
            moviesPanel.setVisible(true);
        });

        manageSessionsButton.addActionListener(e -> {
            SessionsAdminPanel sessionsPanel = new SessionsAdminPanel();
            sessionsPanel.setVisible(true);
        });

        manageRoomsButton.addActionListener(e -> {
            RoomsAdminPanel roomsPanel = new RoomsAdminPanel();
            roomsPanel.setVisible(true);
        });

        manageBarButton.addActionListener(e -> {
            BarProductAdminPanel barPanel = new BarProductAdminPanel();
            barPanel.setVisible(true);
        });


        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminHomepage().setVisible(true));
    }
}
