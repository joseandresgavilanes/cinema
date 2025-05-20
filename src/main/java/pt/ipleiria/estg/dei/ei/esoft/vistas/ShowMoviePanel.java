package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowMoviePanel extends JPanel {
    private JPanel MoviePanel;
    private JComboBox comboBox1;
    private JButton BuyButton;
    private JLabel lblTitle;
    private JLabel lblDescription;
    private JLabel lblPhoto;

    public void setTitulo(String titulo) {
        lblTitle.setText(titulo);
    }

    public void setDescripcion(String descripcion) {
        lblDescription.setText(descripcion);
    }

    public void setHorarios(List<String> horarios) {
        comboBox1.removeAllItems();
        for (String h : horarios) {
            comboBox1.addItem(h);
        }
    }
    public ShowMoviePanel() {
        add(MoviePanel);

        BuyButton.addActionListener(e -> {
            BuyTickets ventana = new BuyTickets();
            ventana.setVisible(true);
        });;
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
