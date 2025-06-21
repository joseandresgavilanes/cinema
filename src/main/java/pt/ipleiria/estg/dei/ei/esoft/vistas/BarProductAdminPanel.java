package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;
import pt.ipleiria.estg.dei.ei.esoft.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BarProductAdminPanel extends JFrame {
    private JTable productsTable;
    private JButton addProductButton;
    private JPanel mainPanel;
    private JButton updateProductButton;
    private JButton deleteProductButton;

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
        updateProductButton   = new JButton("Edit Selected");
        deleteProductButton = new JButton("Delete Selected");
        JPanel btnPanel = new JPanel();
        btnPanel.add(addProductButton);
        btnPanel.add(updateProductButton);
        btnPanel.add(deleteProductButton);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);


        addProductButton.addActionListener(e -> {
            CreateProductForm createForm = new CreateProductForm(() -> {
                loadProducts((DefaultTableModel) productsTable.getModel());
            }, null);
            createForm.setVisible(true);
        });

        add(mainPanel);

        updateProductButton.addActionListener(e -> {
            int row = productsTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this,
                        "Please select a product to edit.",
                        "No selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            Product p = DataStore.getInstance().getProducts().get(row);
            new CreateProductForm(() -> loadProducts(tableModel), p)
                    .setVisible(true);
        });

        deleteProductButton.addActionListener(e -> {
            int row = productsTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this,
                        "Please select a product to delete.",
                        "No selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this product?",
                    "Confirm delete",
                    JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;

            Product p = DataStore.getInstance().getProducts().get(row);
            DataStore.getInstance().removeProduct(p);
            loadProducts(tableModel);
        });


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
