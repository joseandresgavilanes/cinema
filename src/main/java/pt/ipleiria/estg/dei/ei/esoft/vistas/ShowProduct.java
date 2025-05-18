package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Product;

import javax.swing.*;
import java.util.List;

public class ShowProduct extends JFrame {
    private JPanel ProductContainer;
    private JPanel ProductPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShowProduct().setVisible(true));
    }

    public ShowProduct() {
        setContentPane(ProductPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        ProductContainer.setLayout(new BoxLayout(ProductContainer, BoxLayout.Y_AXIS));

        // Obtengo la lista de productos del DataStore
        List<Product> productos = DataStore.getInstance().getProducts();

        for (Product p : productos) {
            ShowItemProductPanel panel = new ShowItemProductPanel();
            panel.setName(       p.getName()       );
            panel.setDescription(p.getDescription());
            panel.setPrice(      p.getPrice()      );
            panel.setOpaque(false);
            ProductContainer.add(panel);
        }

        ProductContainer.revalidate();
        ProductContainer.repaint();
    }

}
