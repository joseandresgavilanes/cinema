package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;

public class Statistics extends JFrame {
    private JPanel statistics;
    private JLabel totalRevenue;
    private JLabel ticketsSold;
    private JLabel mostPopularProduct;

    public Statistics() {
        setContentPane(statistics);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        statistics.setBackground(Color.DARK_GRAY);


        ImageIcon icon = new ImageIcon(getClass().getResource("/images/chart.png"));
        Image scaledImage = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // ajusta el tamaño a gusto
        ticketsSold.setIcon(new ImageIcon(scaledImage));

        ImageIcon iconTicketsSold = new ImageIcon(getClass().getResource("/images/ticket.png"));
        Image scaledImageTicketsSold = iconTicketsSold.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // ajusta el tamaño a gusto
        mostPopularProduct.setIcon(new ImageIcon(scaledImageTicketsSold));


        ImageIcon iconMostPopularProduct = new ImageIcon(getClass().getResource("/images/pipocas.png"));
        Image scaledImageMostPopularProduct = iconMostPopularProduct.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // ajusta el tamaño a gusto
        totalRevenue.setIcon(new ImageIcon(scaledImageMostPopularProduct));


    }

}
