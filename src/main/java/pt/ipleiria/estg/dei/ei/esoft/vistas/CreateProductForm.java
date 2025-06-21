package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Product;
import pt.ipleiria.estg.dei.ei.esoft.models.ProductBarCategory;

import javax.swing.*;
import java.awt.*;

public class CreateProductForm extends JFrame {
    private JPanel productPanel;
    private JComboBox<ProductBarCategory> categoryOptions;
    private JTextField descriptionInput;
    private JButton saveProduct;
    private JButton cancelButton;
    private JTextField nameInput;
    private JTextField quantityInput;
    private JTextField priceInput;
    private JTextField imagePath;

    private final Runnable onProductCreated;
    private final Product productEdit;

    public CreateProductForm(Runnable onProductCreated, Product productEdit) {
        this.onProductCreated = onProductCreated;
        this.productEdit = productEdit;

        productPanel.setBackground(Color.DARK_GRAY);

        setTitle( productEdit != null ? "Edit Movie" : "Create Movie");
        setContentPane(productPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        for (ProductBarCategory category : ProductBarCategory.values()) {
            categoryOptions.addItem(category);
        }

        if (productEdit != null) {
            nameInput.setText(productEdit.getName());
            priceInput.setText(String.valueOf(productEdit.getPrice()));
            categoryOptions.setSelectedItem(productEdit.getCategory());
            descriptionInput.setText(productEdit.getDescription());
            quantityInput.setText(String.valueOf(productEdit.getQuantity()));
            //poner path de la imagen actual
        }

        saveProduct.addActionListener(e -> {
            String name = nameInput.getText().trim();
            String quantityText = quantityInput.getText().trim();
            String priceText = priceInput.getText().trim();
            String description = descriptionInput.getText().trim();
            ProductBarCategory category = (ProductBarCategory) categoryOptions.getSelectedItem();
            String imagePathText = imagePath.getText().trim();

            if (name.isEmpty() || description.isEmpty() || quantityText.isEmpty() || priceText.isEmpty() || category == null || imagePathText.isEmpty()|| quantityText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int quantity;
            float price;

            try {
                quantity = Integer.parseInt(quantityText);
                price = Float.parseFloat(priceText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity and Price must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (productEdit != null) {
                // Editar producto existente
                productEdit.setName(name);
                productEdit.setDescription(description);
                productEdit.setCategory(category.getDisplayName());
                productEdit.setPrice(price);
                productEdit.setQuantity(quantity);
                productEdit.setImagePath(imagePathText);

                JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Crear nuevo producto
                Product nuevo = new Product(name, description, category.getDisplayName(), price, "/images/encebollado.png", quantity);
                DataStore.getInstance().addProduct(nuevo);
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            if (onProductCreated != null) {
                onProductCreated.run();
            }

            dispose();
        });
        cancelButton.addActionListener(e -> dispose());



        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateProductForm(null, null).setVisible(true));
    }
}
