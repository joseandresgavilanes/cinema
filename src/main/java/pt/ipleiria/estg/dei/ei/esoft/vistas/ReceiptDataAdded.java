package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ReceiptDataAdded extends JPanel {
    private JPanel panel1;
    private JLabel productID;
    private JLabel quantity;
    private JLabel description;
    private JLabel singlePrice;
    private JLabel total;

    public ReceiptDataAdded() {
        add(panel1);

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        Dimension standardSize = new Dimension(100, 30);
        Font font = new Font("SansSerif", Font.PLAIN, 12);

        JLabel[] labels = {productID, quantity, description, singlePrice, total};
        for (JLabel label : labels) {
            label.setBorder(border);
            label.setPreferredSize(standardSize);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(font);
        }
    }

    public void setProductID(String id) {
        productID.setText(id);
    }

    public void setQuantity(String qty) {
        quantity.setText(qty);
    }

    public void setDescription(String desc) {
        description.setText(desc);
    }

    public void setSinglePrice(String price) {
        singlePrice.setText(price);
    }

    public void setTotal(String totalValue) {
        total.setText(totalValue);
    }
}
