package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.SessionManager;
import pt.ipleiria.estg.dei.ei.esoft.models.TicketType;
import pt.ipleiria.estg.dei.ei.esoft.models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PaymentWindow extends JFrame {
    private final Map<TicketType, Integer> selectedTickets;
    private final List<String> selectedSeats;
    private final JButton payButton = new JButton("Pay");
    private static int nextReceiptNumber = 1;

    public PaymentWindow(Map<TicketType, Integer> selectedTickets, List<String> selectedSeats) {
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
            sb.append("👤 Client: ").append(SessionManager.getCurrentUser().getUsername()).append("\n\n");
        } else {
            sb.append("👤 Client: [Not logged in]\n\n");
        }

        sb.append("🎟️ Selected Tickets:\n");
        for (Map.Entry<TicketType, Integer> entry : selectedTickets.entrySet()) {
            double subtotal = entry.getKey().getBasePrice() * entry.getValue();
            sb.append(String.format(" - %-8s x %d = %.2f €\n", entry.getKey(), entry.getValue(), subtotal));
            total += subtotal;
        }

        sb.append("\n💺 Selected Seats:\n");
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
                // Close the current window
                this.dispose();

                // Reopen with an active session
                SwingUtilities.invokeLater(() -> {
                    PaymentWindow newWindow = new PaymentWindow(selectedTickets, selectedSeats);
                    newWindow.setVisible(true);
                });
            });
            loginWindow.setVisible(true);
            return;
        }

        User user = SessionManager.getCurrentUser();

        // Simulate current date and time
        Date now = new Date();
        String hour = "21:30"; // fixed time for now
        Float defaultPrice = 6.0f;

        List<pt.ipleiria.estg.dei.ei.esoft.models.Ticket> ticketList = new ArrayList<>();

        int seatIndex = 0;
        for (Map.Entry<TicketType, Integer> entry : selectedTickets.entrySet()) {
            TicketType type = entry.getKey();
            int quantity = entry.getValue();

            for (int i = 0; i < quantity; i++) {
                String seat = seatIndex < selectedSeats.size() ? selectedSeats.get(seatIndex) : "N/A";
                seatIndex++;

                pt.ipleiria.estg.dei.ei.esoft.models.Ticket ticket = new pt.ipleiria.estg.dei.ei.esoft.models.Ticket();
                ticket.setSeat(seat);
                ticket.setDate(now);
                ticket.setHour(hour);
                ticket.setPrice((float) type.getBasePrice());
                // In the future: ticket.setSession(currentSession);
                user.addTicket(ticket);
                ticketList.add(ticket);
            }
        }

        // Create a basic receipt
        pt.ipleiria.estg.dei.ei.esoft.models.Receipt receipt = new pt.ipleiria.estg.dei.ei.esoft.models.Receipt(
                generateReceiptNumber(),
                now,
                user.getUsername(),
                user.getDocument()
        );

        // In the future: receipt.addItem(...) if list functionality is enabled
        user.addReceipt(receipt);

        // Final confirmation
        JOptionPane.showMessageDialog(this, "✅ Payment completed successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }

    private int generateReceiptNumber() {
        return nextReceiptNumber++;
    }
}
