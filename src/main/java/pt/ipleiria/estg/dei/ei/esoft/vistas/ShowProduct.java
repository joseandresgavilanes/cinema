package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Product;
import pt.ipleiria.estg.dei.ei.esoft.models.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ShowProduct extends JFrame {
    private JPanel ProductContainer;
    private JPanel ProductPanel;
    private JPanel Navbar;
    private JButton billboardButton;
    private JButton barButton;
    private JButton loginButton;
    private JLabel image;
    private JButton ticketsButton;
    private JButton receiptsButton;

    public ShowProduct() {
        setTitle("LumiCine – Snacks");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Navbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        Navbar.setBackground(Color.DARK_GRAY);
        ProductPanel = new JPanel(new BorderLayout());
        ProductPanel.add(Navbar, BorderLayout.NORTH);

        image = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledLogo = icon.getImage().getScaledInstance(180, 90, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(scaledLogo));
        Navbar.add(image);

        billboardButton = createNavButton("Billboard");
        barButton       = createNavButton("Snacks");
        loginButton     = createNavButton("Login");
        Navbar.add(billboardButton);
        Navbar.add(barButton);
        Navbar.add(loginButton);

        ProductContainer = new JPanel();
        ProductContainer.setLayout(new BoxLayout(ProductContainer, BoxLayout.Y_AXIS));
        ProductContainer.setBackground(Color.BLACK);
        JScrollPane scroll = new JScrollPane(ProductContainer);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        ProductPanel.add(scroll, BorderLayout.CENTER);

        setContentPane(ProductPanel);

        List<Product> productos = DataStore.getInstance().getProducts();
        for (Product p : productos) {
            ShowItemProductPanel panel = new ShowItemProductPanel();
            panel.setProduct(p);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            ProductContainer.add(panel);
        }

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

        billboardButton.addActionListener(e -> {
            ShowSession billboardWindow = new ShowSession();
            billboardWindow.setVisible(true);
            billboardWindow.setLocationRelativeTo(this);
        });

        if (SessionManager.isLoggedIn()) {
            setupPostLogin();
        }
    }

    private void setupPostLogin() {
        loginButton.setVisible(false);

        if (ticketsButton == null) {
            ticketsButton  = createNavButton("My Tickets");
            receiptsButton = createNavButton("My Receipts");

            ticketsButton.addActionListener(e -> {
                ListTickets ticketsWindow = new ListTickets();
                ticketsWindow.setVisible(true);
            });
            receiptsButton.addActionListener(e -> {
                ListReceipts receiptsWindow = new ListReceipts();
                receiptsWindow.setVisible(true);
            });

            Navbar.add(ticketsButton);
            Navbar.add(receiptsButton);
            Navbar.revalidate();
            Navbar.repaint();
        }
    }

    private JButton createNavButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(229, 9, 20));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShowProduct().setVisible(true));
    }
}
