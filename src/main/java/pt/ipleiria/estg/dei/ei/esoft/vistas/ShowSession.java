package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Session;
import pt.ipleiria.estg.dei.ei.esoft.models.SessionManager;
import pt.ipleiria.estg.dei.ei.esoft.models.User;

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
        setContentPane(SessionPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        Navbar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImage = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(scaledImage));

        MovieContainer.setLayout(new BoxLayout(MovieContainer, BoxLayout.Y_AXIS));
        List<Session> funciones = DataStore.getInstance().getSessions();
        for (Session f : funciones) {
            ShowMoviePanel panel = new ShowMoviePanel();
            panel.setTitulo(      f.getMovie().getTitle()      );
            panel.setDescripcion(f.getMovie().getDescription());
            panel.setPhoto(      f.getMovie().getImagePath()   );
            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            MovieContainer.add(panel);
        }
        MovieContainer.revalidate();
        MovieContainer.repaint();

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

        barButton.addActionListener(e -> {
            ShowProduct showWindow = new ShowProduct();
            showWindow.setVisible(true);
        });
    }

    private void setupPostLogin() {
        loginButton.setVisible(false);

        if (ticketsButton == null) {
            ticketsButton = new JButton("Ver Tickets");
            receiptsButton = new JButton("Ver Receipts");

            ticketsButton.addActionListener(e -> {
                ListTickets ticketsWindow = new ListTickets();
                ticketsWindow.setVisible(true);
            });
//            receiptsButton.addActionListener(e -> {
//                ShowReceipts receiptsWindow = new ShowReceipts();
//                receiptsWindow.setVisible(true);
//            });

            Navbar.add(ticketsButton);
            Navbar.add(receiptsButton);

            // 3) forzar re-layout
            Navbar.revalidate();
            Navbar.repaint();
        }
    }
}
