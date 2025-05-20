package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowProduct extends JFrame {
    private JPanel ProductContainer;
    private JPanel ProductPanel;
    private JPanel Navbar;
    private JButton billboardButton;
    private JButton barButton;
    private JButton loginButton;
    private JLabel image;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShowProduct().setVisible(true));
    }

    public ShowProduct() {
        setContentPane(ProductPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImage = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(scaledImage));

        ProductContainer.setLayout(new BoxLayout(ProductContainer, BoxLayout.Y_AXIS));

        List<Product> productos = DataStore.getInstance().getProducts();

        for (Product p : productos) {
            ShowItemProductPanel panel = new ShowItemProductPanel();
            panel.setName(       p.getName()       );
            panel.setDescription(p.getDescription());
            panel.setPrice(      p.getPrice()      );
            panel.setPhoto(     p.getPhoto()    );
            panel.setOpaque(false);
            ProductContainer.add(panel);
        }

        ProductContainer.revalidate();
        ProductContainer.repaint();

        //mostrar el login
        loginButton.addActionListener(e -> {
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
        });

        billboardButton.addActionListener(e -> {
            ShowSession billboardWindow = new ShowSession();
            billboardWindow.setVisible(true);
        });




    }

}
