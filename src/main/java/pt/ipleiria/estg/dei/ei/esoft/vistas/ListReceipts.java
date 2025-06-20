package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.DataStore;
import pt.ipleiria.estg.dei.ei.esoft.models.Receipt;
import pt.ipleiria.estg.dei.ei.esoft.models.SessionManager;
import pt.ipleiria.estg.dei.ei.esoft.models.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListReceipts extends JFrame {
    private JPanel panel1;
    private JLabel title;
    private JPanel listReceipts;

    private JList<Receipt> receiptJList;
    private DefaultListModel<Receipt> listModel;
    private JButton viewButton;

    public ListReceipts() {
        setTitle("List of Receipts");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel1 = new JPanel(new BorderLayout(10,10));
        title = new JLabel("Your Receipts");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        listReceipts = new JPanel(new BorderLayout(5,5));

        listModel = new DefaultListModel<>();
        receiptJList = new JList<>(listModel);
        receiptJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        receiptJList.setCellRenderer(new ReceiptListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(receiptJList);

        viewButton = new JButton("View Selected Receipt");
        viewButton.addActionListener(e -> viewSelectedReceipt());

        listReceipts.add(scrollPane, BorderLayout.CENTER);
        listReceipts.add(viewButton, BorderLayout.SOUTH);

        panel1.add(title, BorderLayout.NORTH);
        panel1.add(listReceipts, BorderLayout.CENTER);

        setContentPane(panel1);

        loadReceipts();
    }

    private void loadReceipts() {
        listModel.clear();

        User loggedUser = SessionManager.getCurrentUser();

        if (loggedUser != null) {
            for (Receipt r : loggedUser.getReceipts()) {
                listModel.addElement(r);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No user logged in.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void viewSelectedReceipt() {
        Receipt selected = receiptJList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a receipt to view.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ReceiptView view = new ReceiptView(selected);
        view.setVisible(true);
    }

    private static class ReceiptListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Receipt) {
                Receipt r = (Receipt) value;
                setText(String.format("Receipt #%d - %s", r.getReceiptNumber(), r.getClientName()));
            }
            return this;
        }
    }
}
