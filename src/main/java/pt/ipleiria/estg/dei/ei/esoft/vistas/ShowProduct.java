package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.Main;
import pt.ipleiria.estg.dei.ei.esoft.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class ShowProduct extends JFrame {
    private JPanel ProductContainer;
    private JPanel ProductPanel;

    public static void main(String[] args) {
        new ShowProduct().setVisible(true);
    }

    public ShowProduct() {
        setContentPane(ProductPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        ProductContainer.setLayout(new BoxLayout(ProductContainer, BoxLayout.Y_AXIS));

        List<Product> productos = crearProductosDePrueba();

        for (Product p : productos) {
            ShowItemProductPanel panel = new ShowItemProductPanel(); // creado con GUI Designer
            panel.setName(p.getName());
            panel.setDescription(p.getDescription());
            panel.setPrice(p.getPrice());

            ProductContainer.add(panel);
        }

        ProductContainer.revalidate();
        ProductContainer.repaint();
    }

    private List<Product> crearProductosDePrueba() {
        List<Product> productos = new ArrayList<>();
        productos.add(new Product("Combo 1", "Palomitas + Bebida", "Combo", 5.50));
        productos.add(new Product("Refresco", "Coca-Cola 500ml", "Bebida", 2.00));
        productos.add(new Product("Nachos", "Con queso", "Snack", 3.75));
        productos.add(new Product("Comssssssssssssssssssssbo 1", "Palomitas + Bebida", "Combo", 5.50));
        productos.add(new Product("Refresco", "Coca-Cola 500ml", "Bebida", 2.00));
        productos.add(new Product("Nachos", "Con queso", "Snack", 3.75));
        productos.add(new Product("Combo 1", "Palomitas + Bebida", "Combo", 5.50));
        productos.add(new Product("Refresco", "Coca-Cola 500ml", "Bebida", 2.00));
        productos.add(new Product("Nachos", "Con queso", "Snack", 3.75));
        productos.add(new Product("Combo 1", "Palomitas + Bebida", "Combo", 5.50));
        productos.add(new Product("Refresco", "Coca-Cola 500ml", "Bebida", 2.00));
        productos.add(new Product("Nachos", "Con queso", "Snack", 3.75));
        productos.add(new Product("Combo 1", "Palomitas + Bebida", "Combo", 5.50));
        productos.add(new Product("Refresco", "Coca-Cola 500ml", "Bebida", 2.00));
        productos.add(new Product("Nachos", "Con queso", "Snack", 3.75));
        productos.add(new Product("Combo 1", "Palomitas + Bebida", "Combo", 5.50));
        productos.add(new Product("Refresco", "Coca-Cola 500ml", "Bebida", 2.00));
        productos.add(new Product("Nachos", "Con queso", "Snack", 3.75));
        return productos;
    }
}
