package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSelectionDialog extends JDialog {
    private final Map<Product, Integer> selectedProducts = new HashMap<>();

    public ProductSelectionDialog(JFrame parent) {
        super(parent, "Select Products", true);
        setSize(600, 500);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        List<Product> productList = DataStore.getInstance().getProducts();
        for (Product product : productList) {
            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            productPanel.setBackground(Color.WHITE);

            JLabel nameLabel = new JLabel("<html><b>" + product.getName() + "</b><br/>" + product.getDescription() + "<br/>Price: €" + product.getPrice() + "</html>");
            JLabel imageLabel = new JLabel();

            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(product.getPhoto()));
                Image scaled = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaled));
            } catch (Exception e) {
                imageLabel.setText("[No Image]");
            }

            SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 10, 1);
            JSpinner quantitySpinner = new JSpinner(model);
            quantitySpinner.addChangeListener(e -> {
                int quantity = (int) quantitySpinner.getValue();
                if (quantity > 0) {
                    selectedProducts.put(product, quantity);
                } else {
                    selectedProducts.remove(product);
                }
            });

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setBackground(Color.WHITE);
            rightPanel.add(Box.createVerticalStrut(10));
            rightPanel.add(new JLabel("Quantity:"));
            rightPanel.add(quantitySpinner);

            productPanel.add(imageLabel, BorderLayout.WEST);
            productPanel.add(nameLabel, BorderLayout.CENTER);
            productPanel.add(rightPanel, BorderLayout.EAST);

            mainPanel.add(productPanel);
            mainPanel.add(Box.createVerticalStrut(5));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JButton confirmButton = new JButton("Add to Receipt");
        confirmButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(confirmButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    public Map<Product, Integer> getSelectedProducts() {
        return selectedProducts;
    }
}
