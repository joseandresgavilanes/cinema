package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;

public class ShowItemProductPanel extends JPanel {
    private JPanel BarItemPanel;
    private JButton addItemButton;
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblPrice;
    private JLabel lblPhoto;

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

    public void setPhoto(String photoPath) {
        java.net.URL imgURL = getClass().getResource(photoPath);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            lblPhoto.setIcon(icon);
            lblPhoto.setText(null);
        } else {
            lblPhoto.setText("Image not found");
        }
    }

}
