package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.*;

import javax.swing.*;
import java.awt.Image;
import java.math.BigDecimal;
import java.util.Date;

public class ShowItemProductPanel extends JPanel {
    private JPanel BarItemPanel;
    private JButton addItemButton;    // lo usaremos como “Buy”
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblPrice;
    private JLabel lblPhoto;

    // 1) campo para guardar el producto asociado
    private Product product;

    public ShowItemProductPanel() {
        add(BarItemPanel);

        // 2) renombramos el texto del botón
        addItemButton.setText("Buy");

        // 3) listener de compra directa
        addItemButton.addActionListener(e -> {
            // 3.1) si no está logueado, forzamos login y reintentamos
            if (!SessionManager.isLoggedIn()) {
                Login login = new Login(() -> SwingUtilities.invokeLater(() -> addItemButton.doClick()));
                login.setVisible(true);
                return;
            }

            // 3.2) crear recibo con un único ítem
            User user = SessionManager.getCurrentUser();
            int nextNumber = DataStore.getInstance().getReceipts().size() + 1;
            Receipt receipt = new Receipt(
                    nextNumber,
                    new Date(),
                    user.getUsername(),
                    user.getDocument()
            );
            // unwrap price y quantity=1
            ReceiptItem item = new ReceiptItem(
                    product.getName(),        // productID
                    1,                        // quantity
                    product.getName(),        // descripción
                    BigDecimal.valueOf(product.getPrice())      // BigDecimal
            );
            receipt.addItem(item);

            // 3.3) persiste en DataStore y en el usuario
            DataStore.getInstance().addReceipt(receipt, user);

            // 3.4) confirma al usuario
            JOptionPane.showMessageDialog(
                    this,
                    "✅ Comprado “" + product.getName() + "” correctamente.",
                    "Compra realizada",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }

    // 4) nuevo método para asignar el Product y rellenar labels
    public void setProduct(Product p) {
        this.product = p;
        lblName.setText(p.getName());
        lblDescription.setText(p.getDescription());
        lblPrice.setText(p.getPrice() + " €");

        java.net.URL imgURL = getClass().getResource(p.getPhoto());
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaled = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblPhoto.setIcon(new ImageIcon(scaled));
            lblPhoto.setText(null);
        } else {
            lblPhoto.setText("Image not found");
        }
    }
}
