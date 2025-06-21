package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.*;

import javax.swing.*;
import java.awt.Image;
import java.math.BigDecimal;
import java.util.Date;

public class ShowItemProductPanel extends JPanel {
    private JPanel BarItemPanel;
    private JButton addItemButton;
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblPrice;
    private JLabel lblPhoto;

    private Product product;

    public ShowItemProductPanel() {
        add(BarItemPanel);

        addItemButton.setText("Buy");

        addItemButton.addActionListener(e -> {
            if (!SessionManager.isLoggedIn()) {
                Login login = new Login(() -> SwingUtilities.invokeLater(() -> addItemButton.doClick()));
                login.setVisible(true);
                return;
            }

            User user = SessionManager.getCurrentUser();
            int nextNumber = DataStore.getInstance().getReceipts().size() + 1;
            Receipt receipt = new Receipt(
                    nextNumber,
                    new Date(),
                    user.getUsername(),
                    user.getDocument()
            );
            ReceiptItem item = new ReceiptItem(
                    product.getName(),
                    1,
                    product.getName(),
                    BigDecimal.valueOf(product.getPrice())
            );
            receipt.addItem(item);

            DataStore.getInstance().addReceipt(receipt, user);

            JOptionPane.showMessageDialog(
                    this,
                    "✅ Puchased “" + product.getName() + "” correctly.",
                    "Purchased",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }

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
