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
    private final JButton payButton = new JButton("Pagar");
    private final JButton infoButton = new JButton("Info"); // ✅ ELIMINAR
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
            sb.append("👤 Cliente: ").append(SessionManager.getCurrentUser().getUsername()).append("\n\n");
        } else {
            sb.append("👤 Cliente: [No has iniciado sesión]\n\n");
        }

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

        // Botón pagar (inicia con lógica condicional)
        payButton.addActionListener(e -> handlePayment());

        JButton addProductButton = new JButton("Add Products");
        addProductButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Esta funcionalidad estará disponible próximamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(addProductButton);
        buttonPanel.add(infoButton); // ✅ ELIMINAR
        infoButton.addActionListener(e -> {
            if (!SessionManager.isLoggedIn()) {
                JOptionPane.showMessageDialog(this, "No hay usuario logueado.", "Info Usuario", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            User user = SessionManager.getCurrentUser();
            StringBuilder infoText = new StringBuilder();

            infoText.append("👤 Usuario logueado:\n");
            infoText.append(" - Nombre: ").append(user.getUsername()).append("\n");
            infoText.append(" - Documento: ").append(user.getDocument()).append("\n\n");

            List<pt.ipleiria.estg.dei.ei.esoft.models.Ticket> userTickets = user.getTickets();

            infoText.append("🎟️ Tickets asociados previamente:\n");
            if (userTickets.isEmpty()) {
                infoText.append(" - (El usuario no tiene tickets asociados aún)\n");
            } else {
                for (pt.ipleiria.estg.dei.ei.esoft.models.Ticket t : userTickets) {
                    infoText.append(String.format(
                            " - Asiento: %s | Precio: %.2f€ | Fecha: %s | Hora: %s\n",
                            t.getSeat(),
                            t.getPrice(),
                            t.getDate(),
                            t.getHour()
                    ));
                }
            }

            JOptionPane.showMessageDialog(this, infoText.toString(), "Información del Usuario", JOptionPane.INFORMATION_MESSAGE);
        }); // ELIMINAR

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
                // Cerramos esta ventana actual
                this.dispose();

                // Reabrimos con sesión activa
                SwingUtilities.invokeLater(() -> {
                    PaymentWindow newWindow = new PaymentWindow(selectedTickets, selectedSeats);
                    newWindow.setVisible(true);
                });
            });
            loginWindow.setVisible(true);
            return;
        }

        User user = SessionManager.getCurrentUser();

        // Simulación de datos para ahora
        Date now = new Date();
        String hour = "21:30"; // hora fija por ahora
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
                // En el futuro podrás hacer: ticket.setSession(currentSession);
                user.addTicket(ticket);
                ticketList.add(ticket);
            }
        }

        // Crear recibo básico
        pt.ipleiria.estg.dei.ei.esoft.models.Receipt receipt = new pt.ipleiria.estg.dei.ei.esoft.models.Receipt(
                generateReceiptNumber(),
                now,
                user.getUsername(),
                user.getDocument()
        );

        // En el futuro podrías hacer: receipt.addItem(...) si habilitas la lista
        user.addReceipt(receipt);

        // Confirmación final
        JOptionPane.showMessageDialog(this, "✅ ¡Pago realizado con éxito!", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
    private int generateReceiptNumber() {
        return nextReceiptNumber++;
    }

}
