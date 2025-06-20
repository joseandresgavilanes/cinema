package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.Session;

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
    private Session session;

    public ShowMoviePanel(Session session) {
        this.session = session;


        lblPhoto.setPreferredSize(new Dimension(120, 180));
        lblPhoto.setHorizontalAlignment(JLabel.CENTER);
        lblPhoto.setVerticalAlignment(JLabel.CENTER);
        lblPhoto.setForeground(Color.WHITE);
        add(lblPhoto, BorderLayout.WEST);

        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblDescription.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDescription.setForeground(Color.LIGHT_GRAY);
        lblDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblDescription.setMaximumSize(new Dimension(800, 50));

        comboBox1.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBox1.setMaximumSize(new Dimension(200, 30));

        BuyButton.setBackground(new Color(229, 9, 20));
        BuyButton.setFocusPainted(false);
        BuyButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        BuyButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        BuyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        MoviePanel.setPreferredSize(new Dimension(500, 180));

        add(MoviePanel, BorderLayout.CENTER);

        BuyButton.addActionListener(e -> {
            SelectTickets  ventana = new SelectTickets ();
            ventana.setVisible(true);
        });



    }

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

    public void setPhoto(String photoPath) {
        java.net.URL imgURL = getClass().getResource(photoPath);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaledImage = icon.getImage().getScaledInstance(120, 180, Image.SCALE_SMOOTH);
            lblPhoto.setIcon(new ImageIcon(scaledImage));
            lblPhoto.setText(null);
        } else {
            lblPhoto.setText("No image found");
        }
    }

}
