package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;

public class BuyProduct extends JFrame {
    private JPanel panel1;
    private JLabel title;
    private JLabel publicityCombo;
    private JPanel combos;
    private JLabel publicityProducts;
    private JPanel products;
    private JLabel priceLabel;
    private JLabel priceCummulative;
    private JButton backBuyTicket;

    public BuyProduct() {
        Dimension buttonSize = new Dimension(120, 30);
        backBuyTicket.setPreferredSize(buttonSize);

        backBuyTicket.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(panel1);
            if (window != null) {
                window.dispose();
            }
//            new BuyTickets();
        });
    }

    public JPanel getPanel() {
        return panel1;
    }

    public static void main(String[] args) {
        // Set a basic Swing look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Buy Product");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BuyProduct buyProduct = new BuyProduct();

        frame.setContentPane(buyProduct.getPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

