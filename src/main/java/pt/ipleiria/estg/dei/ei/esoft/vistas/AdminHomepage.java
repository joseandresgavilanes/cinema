package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;

public class AdminHomepage extends JFrame{
    private JButton manageSessionsButton;
    private JButton manageMoviesButton;
    private JButton statisticsButton;
    private JButton viewReceiptsButton;
    private JButton manageRoomsButton;
    private JButton manageBarButton;
    private JLabel image;
    private JPanel admin;

    public AdminHomepage() {
        setContentPane(admin);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImage = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH); // ajusta el tamaño a gusto
        image.setIcon(new ImageIcon(scaledImage));


        pack();
        setLocationRelativeTo(null); // centrar ventana


    }


    public static void main(String[] args) {
        new CreateMovieForm().setVisible(true);

    }
}
