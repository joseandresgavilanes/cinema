package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BarProductAdminPanel extends JFrame {
    private JTable productsTable;
    private JButton addProductButton;
    private JPanel mainPanel;

    public BarProductAdminPanel() {
        setTitle("Bar Products Administration");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"Name", "Description", "Category", "Price (€)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        productsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(productsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        loadProducts(tableModel);

        addProductButton = new JButton("Add New Product");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addProductButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        addProductButton.addActionListener(e -> {
            CreateProductForm createForm = new CreateProductForm(() -> {
                loadProducts((DefaultTableModel) productsTable.getModel());
            });
            createForm.setVisible(true);
        });

        add(mainPanel);
    }

    private void loadProducts(DefaultTableModel model) {
        model.setRowCount(0);
        List<Product> products = DataStore.getInstance().getProducts();

        for (Product product : products) {
            model.addRow(new Object[]{
                    product.getName(),
                    product.getDescription(),
                    product.getCategory(),
                    String.format("%.2f", product.getPrice())
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BarProductAdminPanel().setVisible(true));
    }
}
