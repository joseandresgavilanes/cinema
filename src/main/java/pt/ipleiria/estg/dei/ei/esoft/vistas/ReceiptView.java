package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.Receipt;
import pt.ipleiria.estg.dei.ei.esoft.models.ReceiptItem;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class ReceiptView extends JFrame {
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

    public ReceiptView(Receipt receipt) {
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        countingSellings.setLayout(new BoxLayout(countingSellings, BoxLayout.Y_AXIS));

        receiptNumber.setText(String.valueOf(receipt.getReceiptNumber()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date.setText(sdf.format(receipt.getDateTime()));

        clientNameInput.setText(receipt.getClientName());
        clientDocumentInput.setText(receipt.getClientDocument());

        for (ReceiptItem item : receipt.getItems()) {
            ReceiptDataAdded entry = new ReceiptDataAdded();
            entry.setProductID(item.getProductID());
            entry.setQuantity(String.valueOf(item.getQuantity()));
            entry.setDescription(item.getDescription());
            entry.setSinglePrice(item.getSinglePrice().toString());
            entry.setTotal(item.getTotalPrice().toString());

            countingSellings.add(entry);
            countingSellings.add(Box.createVerticalStrut(10));
        }

        countingSellings.revalidate();
        countingSellings.repaint();
    }
}
