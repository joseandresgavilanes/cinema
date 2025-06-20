package pt.ipleiria.estg.dei.ei.esoft.vistas;

import pt.ipleiria.estg.dei.ei.esoft.models.SessionManager;
import pt.ipleiria.estg.dei.ei.esoft.models.TicketType;
import pt.ipleiria.estg.dei.ei.esoft.models.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PaymentWindow extends JFrame {
    private final Map<TicketType, Integer> selectedTickets;
    private final List<String> selectedSeats;

    public PaymentWindow(Map<TicketType, Integer> selectedTickets, List<String> selectedSeats) {
        this.selectedTickets = selectedTickets;
        this.selectedSeats = selectedSeats;

        setTitle("Payment");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 🔐 Verificamos que haya sesión activa
        if (!SessionManager.isLoggedIn()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debes iniciar sesión para continuar con el pago.",
                    "Acceso denegado",
                    JOptionPane.WARNING_MESSAGE
            );
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
            this.dispose();
            return;
        }

        User currentUser = SessionManager.getCurrentUser();

        JTextArea summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setBackground(Color.BLACK);
        summaryArea.setForeground(Color.WHITE);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        double total = 0.0;
        StringBuilder sb = new StringBuilder("👤 Cliente: " + currentUser.getUsername() + "\n\n");
        sb.append("🎟️ Tickets seleccionados:\n");

        for (Map.Entry<TicketType, Integer> entry : selectedTickets.entrySet()) {
            double subtotal = entry.getKey().getBasePrice() * entry.getValue();
            sb.append(String.format(" - %-8s x %d = %.2f €\n", entry.getKey(), entry.getValue(), subtotal));
            total += subtotal;
        }

        sb.append("\n🪑 Asientos seleccionados:\n");
        selectedSeats.stream().sorted().forEach(seat -> sb.append(" - ").append(seat).append("\n"));

        sb.append(String.format("\n💰 Total a pagar: %.2f €\n", total));
        summaryArea.setText(sb.toString());

        // Botón Pagar
        JButton payButton = new JButton("Pagar");
        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "✅ ¡Pago realizado con éxito!", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        });

        // Botón Add Products (de momento no hace nada)
        JButton addProductButton = new JButton("Add Products");
        addProductButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Esta funcionalidad estará disponible próximamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        // Panel de botones abajo
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(addProductButton);
        buttonPanel.add(payButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
    }
}
