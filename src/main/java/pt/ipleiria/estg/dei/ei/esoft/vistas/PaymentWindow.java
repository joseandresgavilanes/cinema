package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PaymentWindow extends JFrame {
    private final Session session;
    private final Map<TicketType, Integer> selectedTickets;
    private final List<String> selectedSeats;
    private final JButton payButton = new JButton("Pay");

    public PaymentWindow(Session session, Map<TicketType, Integer> selectedTickets, List<String> selectedSeats) {
        this.session = session;
        this.selectedTickets = selectedTickets;
        this.selectedSeats = selectedSeats;

        setTitle("Payment");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setBackground(Color.BLACK);
        summaryArea.setForeground(Color.WHITE);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        double total = 0.0;
        StringBuilder sb = new StringBuilder();

        if (SessionManager.isLoggedIn()) {
            sb.append(" Client: ").append(SessionManager.getCurrentUser().getUsername()).append("\n\n");
        } else {
            sb.append(" Client: [Not logged in]\n\n");
        }

        sb.append(" Selected Tickets:\n");
        for (Map.Entry<TicketType, Integer> entry : selectedTickets.entrySet()) {
            double subtotal = entry.getKey().getBasePrice() * entry.getValue();
            sb.append(String.format(" - %-8s x %d = %.2f €\n", entry.getKey(), entry.getValue(), subtotal));
            total += subtotal;
        }

        sb.append("\n🚺 Selected Seats:\n");
        selectedSeats.stream().sorted().forEach(seat -> sb.append(" - ").append(seat).append("\n"));
        sb.append(String.format("\n💰 Total to pay: %.2f €\n", total));
        summaryArea.setText(sb.toString());

        // Pay button logic
        payButton.addActionListener(e -> handlePayment());

        JButton addProductButton = new JButton("Add Products");
        addProductButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "This functionality will be available soon.", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(addProductButton);
        buttonPanel.add(payButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void handlePayment() {
        if (!SessionManager.isLoggedIn()) {
            Login loginWindow = new Login(() -> {
                this.dispose();
                SwingUtilities.invokeLater(() -> {
                    PaymentWindow newWindow = new PaymentWindow(session, selectedTickets, selectedSeats);
                    newWindow.setVisible(true);
                });
            });
            loginWindow.setVisible(true);
            return;
        }

        User user = SessionManager.getCurrentUser();
        String paymentMethod = "Tarjeta";

        try {
            List<Ticket> tickets = TicketManager.buyTickets(session, selectedTickets, selectedSeats, user, paymentMethod);
            Receipt receipt = new Receipt(TicketManager.getTickets().size(), new Date(), user.getUsername(), user.getDocument());

            for (Ticket ticket : tickets) {
                String seat = ticket.getSeat();
                TicketType type = ticket.getTicketType();
                String description = "Ticket for seat " + seat + " - " + type.toString();
                BigDecimal unitPrice = BigDecimal.valueOf(type.getBasePrice());
                ReceiptItem item = new ReceiptItem(seat, 1, description, unitPrice);
                receipt.addItem(item);
            }

            user.addReceipt(receipt);

            JOptionPane.showMessageDialog(this, "✅ Payment completed successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Payment Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
