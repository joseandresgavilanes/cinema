package pt.ipleiria.estg.dei.ei.esoft.vistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Receipt extends JFrame {
    private JPanel panel1;
    private JLabel receiptNumber;
    private JLabel date;
    private JLabel clientName;
    private JLabel clientNameInput;
    private JLabel clientDocument;
    private JLabel clientDocumentInput;
    private JLabel receiptAutoIncrement;
    private JLabel dateActual;
    private JLabel productID;
    private JLabel quantity;
    private JLabel description;
    private JLabel singlePrice;
    private JLabel total;
    private JPanel countingSellings;

    public static void main(String[] args) {
        new Receipt().setVisible(true);
    }

    public Receipt() {
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        countingSellings.setLayout(new BoxLayout(countingSellings, BoxLayout.Y_AXIS));

        // Simulate data
        List<String[]> sales = List.of(
                new String[]{"001", "2", "Popcorn", "3.00", "6.00"},
                new String[]{"002", "1", "Soda", "2.00", "2.00"},
                new String[]{"003", "3", "Candy", "1.50", "4.50"}
        );

        for (String[] sale : sales) {
            ReceiptDataAdded entry = new ReceiptDataAdded();
            entry.setProductID(sale[0]);
            entry.setQuantity(sale[1]);
            entry.setDescription(sale[2]);
            entry.setSinglePrice(sale[3]);
            entry.setTotal(sale[4]);

            countingSellings.add(entry);

            countingSellings.add(Box.createVerticalStrut(10));
        }

        countingSellings.revalidate();
        countingSellings.repaint();
    }
}
