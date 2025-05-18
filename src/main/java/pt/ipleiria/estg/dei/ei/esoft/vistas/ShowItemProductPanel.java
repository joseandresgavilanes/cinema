package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;

public class ShowItemProductPanel extends JPanel {
    private JPanel BarItemPanel;
    private JButton addItemButton;
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblPrice;

    public void setName(String name) {
        lblName.setText(name);
    }

    public void setDescription(String description) {
        lblDescription.setText(description);
    }

    public void setPrice(Double price) {
        lblPrice.setText(String.valueOf(price));
    }

    public ShowItemProductPanel() {
        add(BarItemPanel);
    }
}
