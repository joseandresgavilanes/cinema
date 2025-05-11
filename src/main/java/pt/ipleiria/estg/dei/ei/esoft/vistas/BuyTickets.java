package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyTickets extends JFrame {
    private JPanel buyTicketsPanel;
    private JLabel buyTicket;
    private JLabel session;
    private JLabel date;
    private JLabel schedule;
    private JComboBox scheduleAvailable;
    private JLabel room;
    private JLabel seat;
    private JPanel seatSelection;
    private JLabel priceTag;
    private JLabel price;
    private JLabel payment;
    private JComboBox paymentMethodOptions;
    private JLabel clientName;
    private JFormattedTextField clientNameInput;
    private JLabel dateSession;
    private JLabel roomSession;
    private JButton addProduct;
    private JLabel clientDocumentIdentification;
    private JFormattedTextField clientDocIdentifInput;
    private JButton buyNow;

    private static final int ROWS = 5;
    private static final int COLUMNS = 5;

    public BuyTickets() {
        setTitle("Buy Tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(buyTicketsPanel);

        initializeSeatSelector();

        setVisible(true);
    }

    private void initializeSeatSelector() {
        seatSelection.setLayout(new GridLayout(ROWS, COLUMNS, 2, 2));
        seatSelection.setPreferredSize(new Dimension(250, 250));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                String seatLabel = (char)('A' + row) + String.valueOf(col + 1);
                JToggleButton seatButton = new JToggleButton(seatLabel);
                seatButton.setPreferredSize(new Dimension(5, 2));

                seatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (seatButton.isSelected()) {
                            seatButton.setBackground(Color.GREEN);
                        } else {
                            seatButton.setBackground(null);
                        }
                    }
                });

                seatSelection.add(seatButton);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BuyTickets::new);
    }
}
