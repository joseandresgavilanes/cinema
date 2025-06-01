package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.ProductBarCategory;

import javax.swing.*;
import java.awt.*;

public class CreateProductForm extends JFrame {
    private JPanel productPanel;
    private JComboBox<ProductBarCategory> categoryOptions;
    private JTextField descriptionInput;
    private JLabel imageUpload;
    private JButton addProduct;
    private final Runnable onProductCreated;

    public CreateProductForm(Runnable onProductCreated) {
        this.onProductCreated = onProductCreated;
        productPanel.setBackground(Color.DARK_GRAY);


        setContentPane(productPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (ProductBarCategory category : ProductBarCategory.values()) {
            categoryOptions.addItem(category);
        }

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateProductForm(null).setVisible(true));
    }
}
