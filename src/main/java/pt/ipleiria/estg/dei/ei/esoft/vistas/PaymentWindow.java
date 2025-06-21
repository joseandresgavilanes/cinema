package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class PaymentWindow extends JFrame {
    private final Session session;
    private final Map<TicketType, Integer> selectedTickets;
    private final List<String> selectedSeats;
    private Map<Product, Integer> selectedProducts = new HashMap<>();
    private final JButton payButton = new JButton("Pay");
    private final JTextArea summaryArea = new JTextArea();

    public PaymentWindow(Session session, Map<TicketType, Integer> selectedTickets, List<String> selectedSeats) {
        this.session = session;
        this.selectedTickets = selectedTickets;
        this.selectedSeats = selectedSeats;

        // Restaura productos si venimos del login
        this.selectedProducts = TemporaryProductStore.getSelectedProducts();

        setTitle("Payment");
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        summaryArea.setEditable(false);
        summaryArea.setBackground(Color.BLACK);
        summaryArea.setForeground(Color.WHITE);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        updateSummary();

        payButton.addActionListener(e -> handlePayment());

        JButton addProductButton = new JButton("Add Products");
        addProductButton.addActionListener(e -> {
            ProductSelectionDialog dialog = new ProductSelectionDialog(this);
            dialog.setVisible(true);

            Map<Product, Integer> newlySelected = dialog.getSelectedProducts();
            for (Map.Entry<Product, Integer> entry : newlySelected.entrySet()) {
                selectedProducts.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }

            // Guarda productos temporalmente por si va a login
            TemporaryProductStore.setSelectedProducts(selectedProducts);
            updateSummary();
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
    private double calculateDiscountedTicketTotal() {
        double total = 0.0;

        for (Map.Entry<TicketType, Integer> entry : selectedTickets.entrySet()) {
            double base = entry.getKey().getBasePrice();
            int qty = entry.getValue();
            total += base * qty;
        }

        // Si hay productos y tickets => aplica 10% descuento
        if (!selectedProducts.isEmpty() && !selectedTickets.isEmpty()) {
            total *= 0.9;  // 10% descuento
        }

        return total;
    }


    private void updateSummary() {
        StringBuilder sb = new StringBuilder();
        double total = 0.0;

        if (SessionManager.isLoggedIn()) {
            sb.append(" Client: ").append(SessionManager.getCurrentUser().getUsername()).append("\n\n");
        } else {
            sb.append(" Client: [Not logged in]\n\n");
        }

        sb.append(" Selected Tickets:\n");
        double ticketSubtotal = 0.0;
        for (Map.Entry<TicketType, Integer> entry : selectedTickets.entrySet()) {
            double subtotal = entry.getKey().getBasePrice() * entry.getValue();
            sb.append(String.format(" - %-8s x %d = %.2f €\n", entry.getKey(), entry.getValue(), subtotal));
            ticketSubtotal += subtotal;
        }

        sb.append("\n Selected Seats:\n");
        selectedSeats.stream().sorted().forEach(seat -> sb.append(" - ").append(seat).append("\n"));

        double productSubtotal = 0.0;
        if (!selectedProducts.isEmpty()) {
            sb.append("\n🛍 Products:\n");
            for (Map.Entry<Product, Integer> entry : selectedProducts.entrySet()) {
                Product p = entry.getKey();
                int qty = entry.getValue();
                double subtotal = p.getPrice() * qty;
                sb.append(String.format(" - %s x %d = %.2f €\n", p.getName(), qty, subtotal));
                productSubtotal += subtotal;
            }
        }

        boolean combo = !selectedProducts.isEmpty() && !selectedTickets.isEmpty();
        if (combo) {
            sb.append(String.format("\n🎁 Combo discount applied: -%.2f €", ticketSubtotal * 0.10));
            ticketSubtotal *= 0.9; // apply discount
        }

        total = ticketSubtotal + productSubtotal;
        sb.append(String.format("\n\n💰 Total to pay: %.2f €\n", total));
        summaryArea.setText(sb.toString());
    }


    private void handlePayment() {
        if (!SessionManager.isLoggedIn()) {
            TemporaryProductStore.setSelectedProducts(selectedProducts);  // guarda antes del login
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
        String paymentMethod = "Card";

        try {
            List<Ticket> tickets = TicketManager.buyTickets(session, selectedTickets, selectedSeats, user, paymentMethod);
            Receipt receipt = new Receipt(TicketManager.getTickets().size(), new Date(), user.getUsername(), user.getDocument());

            for (Ticket ticket : tickets) {
                String seat = ticket.getSeat();
                TicketType type = ticket.getTicketType();
                String description = "Ticket for seat " + seat + " - " + type.toString();
                BigDecimal unitPrice = BigDecimal.valueOf(type.getBasePrice());
                receipt.addItem(new ReceiptItem(seat, 1, description, unitPrice));
            }

            for (Map.Entry<Product, Integer> entry : selectedProducts.entrySet()) {
                Product p = entry.getKey();
                int qty = entry.getValue();
                receipt.addItem(new ReceiptItem(p.getName(), qty, "Product: " + p.getName(), BigDecimal.valueOf(p.getPrice())));
            }

            user.addReceipt(receipt);
            TemporaryProductStore.clear(); // limpia cache
            JOptionPane.showMessageDialog(this, "✅ Payment completed successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Payment Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}